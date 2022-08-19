# BoundService_DevComentry

Service
1.Bound Service
- Tạo 1 class MyBoundService extents Service
- Service này có chức năng thực hiện tính toán phép Sum và Sub

- Trong class MyBoundService gồm có :
+ Method onBind() : Nó sẽ trả về một instance của Ibinder
+ Method onCreate() : nơi khởi tạo MyBoundService
+ Method onDestroy() : kết thúc vòng đời của MyBoundService
+ Hai hàm tính sub và sum của 2 số
+ Tạo 1 class tên LocalBinder exten Binder : Dùng để get ra một MyBoundService;


- Trong manifest khai báo service cùng cấp với activity :
![image](https://user-images.githubusercontent.com/65121835/185561614-dd94c1b8-d772-4323-858f-c37a14372292.png)

2.Trong MainActivity
- Tạo một instance ServiceConnection
![image](https://user-images.githubusercontent.com/65121835/185561801-024d9fd4-ef75-4be7-9cb2-9924152aca95.png)

- Thứ tự thực hiện trong MainActivity và MyBoundService
![image](https://user-images.githubusercontent.com/65121835/185561987-a67cd245-9037-49fb-b696-62490ed64d6b.png)

+ onCreate() : MainActivity
+ onStart() : MainActivity
+ onCreate() : MyBoundService
+ onBind() : MyBoundService
+ onServiceConnected : MainActivity
=> Lúc này đã connect được MyBoundService thì ta chỉ việc sử dụng những func của MyBoundService như sum() và sub()
