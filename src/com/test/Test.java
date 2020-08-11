package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.pojo.Flower;

public class Test {
	public static void main(String[] args) throws IOException {
		/*
		 *在MyBatis 运行开始时需要先通过Resources 加载全局配置文件.
		实例化SqlSessionFactoryBuilder构建器.
		帮助SqlSessionFactory接口实现类DefaultSqlSessionFactory.
		在实例化DefaultSqlSessionFactory之前需要先创建XmlConfigBuilder解析全局配置文件流, 并把解析结果存放在Configuration中.
		之后把Configuratin 传递给DefaultSqlSessionFactory.到此SqlSessionFactory 工厂创建成功.
		由SqlSessionFactory 工厂创建SqlSession.
		每次创建SqlSession 时,都需要由TransactionFactory 创建Transaction对象, 同时还需要创建SqlSession 的执行器Excutor, 
		最后实例化DefaultSqlSession,传递给SqlSession 接口.
		根据项目需求使用SqlSession 接口中的API 完成具体的事务操作.
		如果事务执行失败,需要进行rollback 回滚事务.
		如果事务执行成功提交给数据库.关闭SqlSession
		到此就是MyBatis 的运行原理.(面试官说的.)
		*/
		
		// 构建者设计模式
		InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = factory.openSession();

		// session三种查询方式：第一种
		List<Flower> selectList = sqlSession.selectList("a.b.selAll");
		for (Flower flower : selectList) {
			System.out.println(flower.toString());
		}
		System.out.println("-------------------------");

		// session三种查询方式：第二种
		Flower flower = sqlSession.selectOne("a.b.selById");
		System.out.println(flower.toString());
		System.out.println("-------------------------");


		Integer count = sqlSession.selectOne("a.b.selCountById");
		System.out.println(count);
		System.out.println("-------------------------");


		// session三种查询方式：第三种
		// 把数据库中的哪个列的值当做map的key
		Map<Object, Object> map = sqlSession.selectMap("a.b.c", "name");
		System.out.println(map);

		sqlSession.close();
	}

}
