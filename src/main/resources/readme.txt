说明:
    (1).http一层封装，可单独使用进行http请求工具使用，并统一返回体。
    (2).在1的基础上进行了二次封装，进行了全程http请求监控以及记录。1和2完全解耦，可单独使用1，也可1和2一起使用进行监控。
环境:
    1.JDK1.8    (需要8以及以上版本)
    2.gradle4.5 (4.0以上即可)
使用:
     需拷贝文件如下
        1.com->beyondli->common->utils->NewRequestUtils     (此文件为所有http请求方式的静态工具类)
        2.com->beyondli->common->entity->enums              (1中使用)
        3.com->beyondli->common->entity->po                 (此文件夹下所有文件,1中使用,其中HttpResult为统一返回体)
        4.com->beyondli->service->http                      (此文件夹下所有文件,实现监控记录的service层)
        5.com->beyondli->repository->httplog                (此文件夹下所有文件,实现监控记录的dao层)
        注:需要一张基础表,建表语句在schema-httpclient-log.sql文件中
           如需要实现全部功能,需拷贝以上文件,如仅需使用http调用,仅拷贝1,2,3即可。

     使用方式
        拷贝完以上文件,只要将HttpLogService接口注入,按照传参规则调用其中方法即可。
