## 第一步，引入jar包

```
src/main/resources/lib
```

## 第二步，配置application.properties

```
配置
rest.teant
rest.token
```

#### token怎么获得？

```
src/main/resources/images下的【1,2】图片
```

## 第三步，调用测试类测试

```
ApproveListenTest.testListen()  注册监听
ListenController.approveContent 获取监听内容----会返回businesskey ---这里
需要根据processDefinitionKey来判断是否是你需要的表单数据
getBillContent()   -----根据businesskey获取表单内容
```