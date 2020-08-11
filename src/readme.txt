八. 环境搭建
1. 导入jar
2. 在src 下新建全局配置文件mybatis.xml(编写JDBC 四个变量)
	2.1 没有名称和地址要求
	2.2 在全局配置文件中引入DTD 或schema
		2.2.1 如果导入dtd 后没有提示
				Window--> preference --> XML --> XMl catalog --> add 按钮
3. 新建以mapper结尾的包,在包下新建:实体类名+Mapper.xml
	3.1 文件作用:编写需要执行的SQL 命令
	3.2 把xml文件理解成实现类.
4. 测试结果(只有在单独使用mybatis 时使用,最后ssm 整合时下面代码不需要编写.)
		InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = factory.openSession();



 在MyBatis 运行开始时需要先通过Resources 加载全局配置文件成全局配置文件流.
 
实例化SqlSessionFactoryBuilder构建器.帮助SqlSessionFactory接口实现类DefaultSqlSessionFactory.
在实例化DefaultSqlSessionFactory之前,需要先创建XmlConfigBuilder解析全局配置文件流, 并把解析结果存放在Configuration中.
之后把Configuratin 传递给DefaultSqlSessionFactory.到此SqlSessionFactory 工厂创建成功.

由SqlSessionFactory 工厂创建SqlSession.
每次创建SqlSession 时,都需要由TransactionFactory 创建Transaction对象, 同时还需要创建SqlSession 的执行器Excutor, 
最后实例化DefaultSqlSession传递给SqlSession 接口.

根据项目需求,使用SqlSession 接口中的API 完成具体的事务操作.
如果事务执行失败,需要进行rollback 回滚事务.
如果事务执行成功提交给数据库.关闭SqlSession
到此就是MyBatis 的运行原理.(面试官说的.)