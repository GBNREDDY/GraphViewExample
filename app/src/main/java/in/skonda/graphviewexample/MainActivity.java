package in.skonda.graphviewexample;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        graphView = (GraphView) findViewById(R.id.gv);
        Button line = (Button) findViewById(R.id.button);
        Button bar = (Button) findViewById(R.id.button2);
        Button point = (Button) findViewById(R.id.button3);
        Button mixup = (Button) findViewById(R.id.button4);
        line.setOnClickListener(this);
        bar.setOnClickListener(this);
        point.setOnClickListener(this);
        mixup.setOnClickListener(this);
        graphView.getViewport().setScrollable(true); // enables horizontal scrolling
        graphView.getViewport().setScrollableY(true); // enables vertical scrolling
        graphView.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        graphView.getViewport().setScalableY(true); // enables vertical zooming and scrolling
    }

    @Override
    public void onClick(View v) {
        graphView.removeAllSeries();
        switch (v.getId()) {
            case R.id.button:
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                        new DataPoint(0, 1),
                        new DataPoint(1, 5),
                        new DataPoint(2, 3),
                        new DataPoint(3, 2),
                        new DataPoint(4, 6)
                });
                series.setTitle("Random Curve 1");
                series.setColor(Color.BLUE);
                series.setDrawDataPoints(true);
                series.setDataPointsRadius(10);
                series.setThickness(8);
                /*Paint paint = new Paint();
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(10);
                paint.setPathEffect(new DashPathEffect(new float[]{8, 5}, 0));
                series.setCustomPaint(paint);*/
                graphView.addSeries(series);
                break;
            case R.id.button2:
                BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(0, -1),
                        new DataPoint(1, 5),
                        new DataPoint(2, 3),
                        new DataPoint(3, 2),
                        new DataPoint(4, 6)
                });
                series2.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
                series2.setSpacing(50);
                series2.setDrawValuesOnTop(true);
                series2.setValuesOnTopColor(Color.RED);
                graphView.addSeries(series2);
                break;
            case R.id.button3:
                PointsGraphSeries<DataPoint> series3 = new PointsGraphSeries<>(new DataPoint[]{
                        new DataPoint(0, -2),
                        new DataPoint(1, 5),
                        new DataPoint(2, 3),
                        new DataPoint(3, 2),
                        new DataPoint(4, 6)
                });
                graphView.addSeries(series3);
                series3.setShape(PointsGraphSeries.Shape.POINT);

                PointsGraphSeries<DataPoint> series4 = new PointsGraphSeries<DataPoint>(new DataPoint[]{
                        new DataPoint(0, -1),
                        new DataPoint(1, 4),
                        new DataPoint(2, 2),
                        new DataPoint(3, 1),
                        new DataPoint(4, 5)
                });
                graphView.addSeries(series4);
                series4.setShape(PointsGraphSeries.Shape.RECTANGLE);
                series4.setColor(Color.RED);

                PointsGraphSeries<DataPoint> series5 = new PointsGraphSeries<>(new DataPoint[]{
                        new DataPoint(0, 0),
                        new DataPoint(1, 3),
                        new DataPoint(2, 1),
                        new DataPoint(3, 0),
                        new DataPoint(4, 4)
                });
                graphView.addSeries(series5);
                series5.setShape(PointsGraphSeries.Shape.TRIANGLE);
                series5.setColor(Color.YELLOW);

                PointsGraphSeries<DataPoint> series6 = new PointsGraphSeries<>(new DataPoint[]{
                        new DataPoint(0, 1),
                        new DataPoint(1, 2),
                        new DataPoint(2, 0),
                        new DataPoint(3, -1),
                        new DataPoint(4, 3)
                });
                graphView.addSeries(series6);
                series6.setColor(Color.GREEN);
                series6.setCustomShape(new PointsGraphSeries.CustomShape() {
                    @Override
                    public void draw(Canvas canvas, Paint paint, float x, float y, DataPointInterface dataPoint) {
                        paint.setStrokeWidth(10);
                        canvas.drawLine(x - 20, y - 20, x + 20, y + 20, paint);
                        canvas.drawLine(x + 20, y - 20, x - 20, y + 20, paint);
                    }
                });
                break;
            case R.id.button4:
                BarGraphSeries<DataPoint> series7 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(0, -2),
                        new DataPoint(1, 5),
                        new DataPoint(2, 3),
                        new DataPoint(3, 2),
                        new DataPoint(4, 6)
                });
                series7.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
                series7.setSpacing(50);
                graphView.addSeries(series7);

                LineGraphSeries<DataPoint> series8 = new LineGraphSeries<>(new DataPoint[]{
                        new DataPoint(0, 3),
                        new DataPoint(1, 3),
                        new DataPoint(2, 6),
                        new DataPoint(3, 2),
                        new DataPoint(4, 5)
                });
                graphView.addSeries(series8);
                break;
        }

    }
}
