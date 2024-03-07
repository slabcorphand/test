package jp.co.vermore.service;

import jp.co.vermore.entity.DashboardCoinCount;
import jp.co.vermore.form.admin.DashboardCoinDataForm;
import jp.co.vermore.mapper.DashboardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private DashboardMapper dashboardMapper;

    public List<DashboardCoinDataForm> getCoinDataIn(List<String> timeList) {
        return dashboardMapper.getCoinDataIn(timeList);
    }

    public void insertList(List<DashboardCoinDataForm> dataFormList) {
        dashboardMapper.insertList(dataFormList);
    }

    public void deleteList(Date endDateTime) {
        dashboardMapper.deleteOldData(endDateTime);
    }

    public List<DashboardCoinCount> getNearlySix() {
        return dashboardMapper.getNearlySix();
    }
}
