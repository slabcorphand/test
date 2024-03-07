package jp.co.vermore.mapper;

import jp.co.vermore.entity.DashboardCoinCount;
import jp.co.vermore.form.admin.DashboardCoinDataForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface DashboardMapper {
    List<DashboardCoinDataForm> getCoinDataIn(@Param("list") List<String> timeList);

    void insertList(@Param("list") List<DashboardCoinDataForm> dataFormList);

    void deleteOldData(@Param("endDateTime") Date endDateTime);

    List<DashboardCoinCount> getNearlySix();
}
