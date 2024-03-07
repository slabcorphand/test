package jp.co.vermore.kit.component.chart.model;

import lombok.Data;

/**
 * Created by fgm on 2017/7/9.
 */
@Data
public class XYScatter {
    private double x;
    private double y;
    private String label;


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
