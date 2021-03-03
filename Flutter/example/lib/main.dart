// This is a basic Flutter widget test.
//
// To perform an interaction with a widget in your test, use the WidgetTester
// utility that Flutter provides. For example, you can send tap and scroll
// gestures. You can also use WidgetTester to find child widgets in the widget
// tree, read text, and verify that the values of widget properties are correct.

import 'dart:async';
import 'dart:convert';
import 'dart:io';

import 'package:dio/dio.dart';
import 'package:dokit/dokit.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:path_provider/path_provider.dart';

// before publish: flutter packages pub publish --dry-run
void main() {
  final List<String> blackList = <String>[
    'plugins.flutter.io/sensors/gyroscope',
    'plugins.flutter.io/sensors/user_accel',
    'plugins.flutter.io/sensors/accelerometer'
  ];

  DoKit.runApp(
      app: DoKitApp(MyApp()),
      useInRelease: true,
      logCallback: (String log) {
        // ignore: unused_local_variable
        final String i = log;
      },
      methodChannelBlackList: blackList,
      exceptionCallback: (dynamic obj, StackTrace trace) {
        print('ttt$obj');
      });
  // runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'DoKit Test',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blue,
        // This makes the kit.visual density adapt to the platform that you run
        // the app on. For desktop platforms, the controls will be smaller and
        // closer together (more dense) than on mobile platforms.
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: DoKitTestPage(),
    );
  }
}

class DoKitTestPage extends StatefulWidget {
  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  @override
  _DoKitTestPageState createState() => _DoKitTestPageState();
}

class _DoKitTestPageState extends State<DoKitTestPage> {
  @override
  void initState() {
    super.initState();
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      alignment: Alignment.center,
      color: Colors.white,
      child: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Container(
              decoration: const BoxDecoration(
                  borderRadius: BorderRadius.all(Radius.circular(4)),
                  color: Color(0xffcccccc)),
              margin: const EdgeInsets.only(bottom: 30),
              child: FlatButton(
                child: const Text('Mock Http Post',
                    style: TextStyle(
                      color: Color(0xff000000),
                      fontSize: 18,
                    )),
                onPressed: mockHttpPost,
              ),
            ),
            Container(
              decoration: const BoxDecoration(
                  borderRadius: BorderRadius.all(Radius.circular(4)),
                  color: Color(0xffcccccc)),
              margin: const EdgeInsets.only(bottom: 30),
              child: FlatButton(
                child: const Text('Mock Http Get',
                    style: TextStyle(
                      color: Color(0xff000000),
                      fontSize: 18,
                    )),
                onPressed: mockHttpGet,
              ),
            ),
            Container(
              decoration: const BoxDecoration(
                  borderRadius: BorderRadius.all(Radius.circular(4)),
                  color: Color(0xffcccccc)),
              margin: const EdgeInsets.only(bottom: 30),
              child: FlatButton(
                child: const Text('Test Download',
                    style: TextStyle(
                      color: Color(0xff000000),
                      fontSize: 18,
                    )),
                onPressed: testDownload,
              ),
            ),
            Container(
              decoration: const BoxDecoration(
                  borderRadius: BorderRadius.all(Radius.circular(4)),
                  color: Color(0xffcccccc)),
              margin: const EdgeInsets.only(bottom: 30),
              child: FlatButton(
                child: const Text('Test Method Channel',
                    style: TextStyle(
                      color: Color(0xff000000),
                      fontSize: 18,
                    )),
                onPressed: () {
                  testMethodChannel();
                },
              ),
            ),
            Container(
              decoration: const BoxDecoration(
                  borderRadius: BorderRadius.all(Radius.circular(4)),
                  color: Color(0xffcccccc)),
              margin: const EdgeInsets.only(bottom: 30),
              child: FlatButton(
                child: const Text('Open Route Page',
                    style: TextStyle(
                      color: Color(0xff000000),
                      fontSize: 18,
                    )),
                onPressed: () {
                  Navigator.of(context, rootNavigator: false).push<dynamic>(
                    MaterialPageRoute<dynamic>(
                      builder: (BuildContext context) {
                        //指定跳转的页面
                        return TestPage2();
                      },
                      settings: const RouteSettings(
                          name: 'page1', arguments: <String>['test', '111']),
                    ),
                  );
                },
              ),
            ),
            Container(
              decoration: const BoxDecoration(
                  borderRadius: BorderRadius.all(Radius.circular(4)),
                  color: Color(0xffcccccc)),
              margin: const EdgeInsets.only(bottom: 30),
              child: FlatButton(
                child: const Text('Stop Timer',
                    style: TextStyle(
                      color: Color(0xff000000),
                      fontSize: 18,
                    )),
                onPressed: stopAll,
              ),
            ),
          ],
        ),
      ),
    );
  }

  Timer timer;

  Future<void> testDownload() async {
    const String url =
        'https://pt-starfile.didistatic.com/static/starfile/node20210220/895f1e95e30aba5dd56d6f2ccf768b57/GjzGU0Pvv11613804530384.zip';
    final String savePath = await getPhoneLocalPath();
    const String zipName = 'test.zip';
    final Dio dio = Dio();
    print('$savePath/$zipName');
    // ignore: unused_local_variable
    final Response<dynamic> response = await dio
        .download(url, '$savePath/$zipName',
            onReceiveProgress: (int received, int total) {
      if (total != -1) {
        // 当前下载的百分比
        // print((received / total * 100).toStringAsFixed(0) + "%");
        // print("received=$received total=$total");
        if (received == total) {
          print('下载完成 ✅ ');
        }
      } else {}
    });

    return;
  }

  ///获取手机的存储目录路径
  ///getExternalStorageDirectory() 获取的是  android 的外部存储 （External Storage）
  ///  getApplicationDocumentsDirectory 获取的是 ios 的Documents` or `Downloads` 目录
  Future<String> getPhoneLocalPath() async {
    final Directory directory =
        Theme.of(context).platform == TargetPlatform.android
            ? await getExternalStorageDirectory()
            : await getApplicationDocumentsDirectory();
    return directory.path;
  }

  void testMethodChannel() {
    timer?.cancel();
    timer = Timer.periodic(const Duration(seconds: 2), (Timer timer) async {
      const MethodChannel _kChannel =
          MethodChannel('plugins.flutter.io/package_info');
      // ignore: unused_local_variable
      final Map<String, dynamic> map =
          await _kChannel.invokeMapMethod<String, dynamic>('getAll');
    });
  }

  void stopAll() {
    print('stopAll');
    timer?.cancel();
    timer = null;
  }

  void mockHttpPost() {
    timer?.cancel();
    timer = Timer.periodic(const Duration(seconds: 2), (Timer timer) async {
      final HttpClient client = HttpClient();
      const String url =
          'https://pinzhi.didichuxing.com/kop_stable/gateway?api=hhh';
      final HttpClientRequest request = await client.postUrl(Uri.parse(url));
      final Map<String, String> map1 = <String, String>{};
      map1['v'] = '1.0';
      map1['month'] = '7';
      map1['day'] = '25';
      map1['key'] = 'bd6e35a2691ae5bb8425c8631e475c2a';
      request.add(utf8.encode(json.encode(map1)));
      request.add(utf8.encode(json.encode(map1)));
      final HttpClientResponse response = await request.close();
      // ignore: unused_local_variable
      final String responseBody = await response.transform(utf8.decoder).join();
    });
  }

  void mockHttpGet() {
    timer?.cancel();
    timer = Timer.periodic(const Duration(seconds: 2), (Timer timer) async {
      final HttpClient client = HttpClient();
      const String url = 'https://www.baidu.com';
      final HttpClientRequest request = await client.postUrl(Uri.parse(url));
      final HttpClientResponse response = await request.close();
      // ignore: unused_local_variable
      final String responseBody = await response.transform(utf8.decoder).join();
    });
  }
}

class TestPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return TestPageState();
  }
}

class TestPageState extends State<TestPage> {
  int index = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(),
      body: Center(
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(
          // Column is also a layout widget. It takes a list of children and
          // arranges them vertically. By default, it sizes itself to fit its
          // children horizontally, and tries to be as tall as its parent.
          //
          // Invoke "debug painting" (press "p" in the console, choose the
          // "Toggle Debug Paint" action from the Flutter Inspector in Android
          // Studio, or the "Toggle Debug Paint" command in Visual Studio Code)
          // to see the wireframe for each widget.
          //
          // Column has various properties to control how it sizes itself and
          // how it positions its children. Here we use mainAxisAlignment to
          // center the children vertically; the main axis here is the vertical
          // axis because Columns are vertical (the cross axis would be
          // horizontal).
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            GestureDetector(
              onTap: () {
                Navigator.of(context, rootNavigator: false).push<dynamic>(
                  MaterialPageRoute<dynamic>(
                    builder: (BuildContext context) {
                      //指定跳转的页面
                      return TestPage2();
                    },
                    settings: const RouteSettings(
                        name: 'page1', arguments: <String>['test', '111']),
                  ),
                );
              },
              child: const Text(
                'page1:',
              ),
            ),
            Text(
              '0',
              style: Theme.of(context).textTheme.headline4,
            ),
            Container(height: 100, width: 300)
          ],
        ),
      ),
    );
  }
}

class TestPage2 extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return TestPageState2();
  }
}

class TestPageState2 extends State<TestPage2> {
  int index = 0;

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              const Text(
                'page2:',
              ),
              Text(
                '0',
                style: Theme.of(context).textTheme.headline4,
              )
            ],
          ),
        ),
      ),
    );
  }
}

class TestPage3 extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return TestPageState3();
  }
}

class TestPageState3 extends State<TestPage3> {
  int index = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(),
      body: Center(
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(
          // Column is also a layout widget. It takes a list of children and
          // arranges them vertically. By default, it sizes itself to fit its
          // children horizontally, and tries to be as tall as its parent.
          //
          // Invoke "debug painting" (press "p" in the console, choose the
          // "Toggle Debug Paint" action from the Flutter Inspector in Android
          // Studio, or the "Toggle Debug Paint" command in Visual Studio Code)
          // to see the wireframe for each widget.
          //
          // Column has various properties to control how it sizes itself and
          // how it positions its children. Here we use mainAxisAlignment to
          // center the children vertically; the main axis here is the vertical
          // axis because Columns are vertical (the cross axis would be
          // horizontal).
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            GestureDetector(
              onTap: () {
                Navigator.of(context, rootNavigator: false).push<dynamic>(
                  MaterialPageRoute<dynamic>(
                    builder: (BuildContext context) {
                      //指定跳转的页面
                      return MyApp();
                    },
                    settings: const RouteSettings(name: 'page3'),
                  ),
                );
              },
              child: const Text(
                'page3:',
              ),
            ),
            Text(
              '0',
              style: Theme.of(context).textTheme.headline4,
            )
          ],
        ),
      ),
    );
  }
}
