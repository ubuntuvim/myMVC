# 自定义MVC框架

  模拟struts完成简单的MVC框架，目前暂时只完成了V、C层，M层还未实现。
  
**项目结构如下：**

![项目结构](http://7xnrhh.com1.z0.glb.clouddn.com/QQ%E5%9B%BE%E7%89%8720160303141839.png)

1. LoginAction.java 测试，模拟登陆处理
2. Action.java 框架Action接口
3. ActionManager.java 根据配置的Action类名反射得到实例
4. ActionMapping.java 根据Action配置定义的javabean类，用于保存Action配置信息
5. ActionMappingManager.java 读取、解析Action配置并把配置转换成对应的ActionMapping对象
6. CharactorFilter.java 编码过滤器
7. ActionServlet.java 框架拦截器，根据web.xml的配置拦截请求
8. snails-actions-validate.xsd Action配置的校验文件，此文件限定了Action配置的格式
9. snails-actions.xml Action配置，类似于struts，配置了Action的名称、类、结果页面
10. 依赖jar commons-lang3-3.1.jar dom4j-1.6.1.jar
11. web.xml项目总配置文件
12. fail.jsp 登录失败页面后跳转页面
13. index.jsp登录页面
14. success.jsp 登录成功后跳转页面