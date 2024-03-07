package jp.co.vermore.form.admin;

import jp.co.vermore.common.mvc.BaseEntity;
import lombok.Data;

@Data
public class DashboardCoinDataForm extends BaseEntity {
    private String monthYear;

    private Integer coinAmount;
}
