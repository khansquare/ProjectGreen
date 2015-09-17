package br.liveo.model;

import android.graphics.drawable.Drawable;

/**
 * Author       :   Mohsin Khan
 * Designation  :   Android Developer
 * E-mail       :   khan.square@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 14 , 2015
 * Purpose      :   On report screen, we will show chart for each test, this model will contain the information regarding to reports
 * Description  :   Detailed Description...
 */
public class TestReport {
    private String title;
    private int max;
    private int progress;
    private int progressDrawable;

    public TestReport() {

    }

    public TestReport(String title, int max, int progress, int progressDrawable) {
        this.title = title;
        this.max = max;
        this.progress = progress;
        this.progressDrawable = progressDrawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getProgressDrawable() {
        return progressDrawable;
    }

    public void setProgressDrawable(int progressDrawable) {
        this.progressDrawable = progressDrawable;
    }
}