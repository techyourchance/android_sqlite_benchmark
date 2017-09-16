package com.techyourchance.sqlitebenchmark.screens.test.mvcviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.techyourchance.sqlitebenchmark.R;
import com.techyourchance.sqlitebenchmark.screens.common.mvcviews.BaseViewMvc;
import com.techyourchance.sqlitebenchmark.test.TestResults;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TestViewMvcImpl extends BaseViewMvc<TestViewMvc.ExampleViewMvcListener>
        implements TestViewMvc {

    private final Button mBtnShowDialog;
    private final ProgressBar mProgressTest;
    private final ScatterChart mChartTestResults;


    public TestViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        setRootView(inflater.inflate(R.layout.layout_test, container, false));

        mProgressTest = findViewById(R.id.progress_test);
        mChartTestResults = findViewById(R.id.chart_test_results);
        mBtnShowDialog = findViewById(R.id.btn_start_test);

        mBtnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ExampleViewMvcListener listener : getListeners()) {
                    listener.onStartTestClicked();
                }
            }
        });

        initChart();
    }

    private void initChart() {
        Description description = new Description();
        description.setText("");
        mChartTestResults.setDescription(description);
    }

    @Override
    public void testInProgress() {
        mBtnShowDialog.setEnabled(false);
        mBtnShowDialog.setVisibility(View.GONE);
        mProgressTest.setVisibility(View.VISIBLE);
        mChartTestResults.setVisibility(View.GONE);
    }

    @Override
    public void testCompleted() {
        mBtnShowDialog.setEnabled(true);
        mBtnShowDialog.setVisibility(View.VISIBLE);
        mProgressTest.setVisibility(View.GONE);
    }

    @Override
    public void bindTestResults(TestResults testResults) {
        mChartTestResults.setVisibility(View.VISIBLE);
        mChartTestResults.setData(new ScatterData(convertTestResultsToScatterData(testResults)));
    }

    private IScatterDataSet convertTestResultsToScatterData(TestResults testResults) {
        List<Entry> entries = new LinkedList<>();

        Map<Integer, Long> stepsResults = testResults.getStepsResults();

        for (int numOfQueriedEntities : stepsResults.keySet()) {
            entries.add(new Entry(numOfQueriedEntities, stepsResults.get(numOfQueriedEntities)));
        }

        ScatterDataSet dataSet = new ScatterDataSet(
                entries,
                getContext().getString(R.string.chart_label, String.valueOf(testResults.getTotalEntities()))
        );
        dataSet.setColor(getContext().getResources().getColor(android.R.color.holo_red_dark));
        return dataSet;
    }
}
