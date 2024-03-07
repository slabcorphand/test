package jp.co.vermore.batch;

import jp.co.vermore.form.admin.DashboardCoinDataForm;
import jp.co.vermore.service.DashboardService;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Configuration
public class DashboardBatch {

    @Autowired
    private DashboardService dashboardService;

    protected static final Logger logger = LoggerFactory.getLogger(DashboardBatch.class);

    @Transactional(rollbackFor = Exception.class)
    public void dashboardStep() {
        logger.info("========== 1 コイン数 step start  ==========");

        logger.info("========== 2 コイン数について統計が開始 ==========");

        // 2023.01.30
        // sLab 張
        // バッチサンプル作成
        // ＊研修項目のデータは偽データですので、バッチ統計期間が「2018-10~2019-03」に設定する。サンプル利用のみ
        List<String> timeList = new ArrayList<>(Arrays.asList("2018-10","2018-11","2018-12","2019-01","2019-02","2019-03"));
        // ＊＊本番環境を設定する時に、期間は「過去半年」です。
        // List<String> timeList = DateUtils.getPastHalfYearMonth();

        // 処理待ちのデータ
        List<DashboardCoinDataForm> dataFormList = dashboardService.getCoinDataIn(timeList);

        // 現在時間の10分前のデータを全部削除
        Date endDateTime = DateUtils.addMinutes(new Date(), -10);

        logger.info("========== 2 コイン数について統計が終了 ==========");

        try {
            logger.info("========== 3 コイン数統計結果をテーブルに挿入開始 ==========");

            dashboardService.insertList(dataFormList);

            logger.info("========== 3 コイン数統計結果をテーブルに挿入終了 ==========");

            logger.info("========== 4 コイン数 古い統計結果をテーブルに削除開始 ==========");

            dashboardService.deleteList(endDateTime);

            logger.info("========== 4 コイン数 古い統計結果をテーブルに削除終了 ==========");

            logger.info("========== 1 コイン数 step end  ==========");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            logger.error("========" + e.getMessage() + "========");
            logger.error("========" + e.toString() + "========");
        }
    }
}
