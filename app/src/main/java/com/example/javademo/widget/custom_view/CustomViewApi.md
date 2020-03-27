
画布 API

Canvas.drawColor(@ColorInt int color) 颜色填充
Canvas.drawCircle(float centerX, float centerY, float radius, Paint paint) 画圆
----前两个参数 centerX centerY 是圆心的坐标，第三个参数 radius 是圆的半径，单位都是像素
Canvas.drawRect(float left, float top, float right, float bottom, Paint paint) 画矩形
Canvas.drawPoint(float x, float y, Paint paint) 画点
drawPoints(float[] pts, int offset, int count, Paint paint) / drawPoints(float[] pts, Paint paint) 画点（批量）
----pts 这个数组是点的坐标，每两个成一对；offset 表示跳过数组的前几个数再开始记坐标；count 表示一共要绘制几个点。
Canvas.drawOval(float left, float top, float right, float bottom, Paint paint) 画椭圆(限水平和数值)
Canvas.drawLine(float startX, float startY, float stopX, float stopY, Paint paint) 画线
----startX, startY, stopX, stopY 分别是线的起点和终点坐标。
Canvas.drawLines(float[] pts, int offset, int count, Paint paint) / drawLines(float[] pts, Paint paint) 画线（批量）
Canvas.drawRoundRect(float left, float top, float right, float bottom, float rx, float ry, Paint paint) 画圆角矩形
----left, top, right, bottom 是四条边的坐标，rx 和 ry 是圆角的横向半径和纵向半径
drawText(String text, float x, float y, Paint paint) 绘制文字
----drawText() 这个方法就是用来绘制文字的。参数 text 是用来绘制的字符串，x 和 y 是绘制的起点坐标。

Canvas.drawArc(float left, float top, float right, float bottom,float startAngle, float sweepAngle, boolean useCenter, Paint paint) 绘制弧形或扇形
----left, top, right, bottom 描述的是这个弧形所在的椭圆；startAngle 是弧形的起始角度
----（x 轴的正向，即正右的方向，是 0 度的位置；顺时针为正角度，逆时针为负角度），
----sweepAngle 是弧形划过的角度；useCenter 表示是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形。

Canvas.drawPath(Path path, Paint paint) 画自定义图形
----用法:    Paint paint = new Paint();
            Path path = new Path(); // 初始化 Path 对象
            //将一组绘画行为用path对象描述, 统一绘制
            {
              // 使用 path 对图形进行描述（这段描述代码不必看懂）
              path.addArc(200, 200, 400, 400, -225, 225);
              path.arcTo(400, 200, 600, 400, -180, 225, false);
              path.lineTo(400, 542);
            }
            在onDraw()里 canvas.drawPath(path, paint); // 绘制出 path 描述的图形（心形）

Paint API

 Paint.setColor(int color)
 Paint.setStyle(Paint.Style style) 绘制模式改为画线模式/填充模式
 Paint.setStrokeWidth(float width) 设置线条的宽度
 Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG); 抗锯齿