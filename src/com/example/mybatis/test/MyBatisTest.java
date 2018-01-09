package com.example.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.mybatis.bean.Employee;
import com.example.mybatis.dao.EmployeeMapper;
import com.example.mybatis.dao.EmployeeMapperAnnotation;

/**
 * 1���ӿ�ʽ��� ԭ���� Dao ====> DaoImpl mybatis�� Mapper ====> xxMapper.xml
 * 
 * 2��SqlSession��������ݿ��һ�λỰ���������رգ�
 * 3��SqlSession��connectionһ�����Ƿ��̰߳�ȫ��ÿ��ʹ�ö�Ӧ��ȥ��ȡ�µĶ���
 * 4��mapper�ӿ�û��ʵ���࣬����mybatis��Ϊ����ӿ�����һ��������� �����ӿں�xml���а󶨣� EmployeeMapper
 * empMapper = sqlSession.getMapper(EmployeeMapper.class); 5��������Ҫ�������ļ���
 * mybatis��ȫ�������ļ����������ݿ����ӳ���Ϣ�������������Ϣ��...ϵͳ���л�����Ϣ sqlӳ���ļ���������ÿһ��sql����ӳ����Ϣ��
 * ��sql��ȡ������
 * 
 * @author JHao
 *
 */
public class MyBatisTest {
	private SqlSessionFactory sessionFactory = null;
	// ���̰߳�ȫ������д����,���ڴ���ô��
	private SqlSession session = null;

	@Before
	public void init() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		sessionFactory = new SqlSessionFactoryBuilder().build(in);
		session = sessionFactory.openSession();
	}

	@After
	public void destroy() {
		session.commit();
		session.close();
	}

	/**
	 * 1.����xml�ļ�(ȫ�������ļ�)����һ��SqlSessionFactory 2��sqlӳ���ļ���������ÿһ��sql���Լ�sql�ķ�װ����ȡ�
	 * 3����sqlӳ���ļ�ע����ȫ�������ļ��� 4��д���룺 1��������ȫ�������ļ��õ�SqlSessionFactory��
	 * 2����ʹ��sqlSession��������ȡ��sqlSession����ʹ������ִ����ɾ�Ĳ�
	 * һ��sqlSession���Ǵ�������ݿ��һ�λỰ������ر�
	 * 3����ʹ��sql��Ψһ��־������MyBatisִ���ĸ�sql��sql���Ǳ�����sqlӳ���ļ��еġ�
	 * 
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// ��ȡSqlSessionʵ��,��ֱ��ִ���Ѿ�ӳ���sql���
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Employee emp = session.selectOne("selectEmp", 1);
			System.out.println(emp);
		} finally {
			session.close();
		}
	}

	@Test
	public void test1() throws IOException {
		// 3����ȡ�ӿڵ�ʵ�������
		// ��Ϊ�ӿ��Զ��Ĵ���һ��������󣬴������ȥִ����ɾ�Ĳ鷽��
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

		Employee employee = mapper.getEmpById(1);
		System.out.println(mapper.getClass());
		System.out.println(employee);
	}

	@Test
	public void test2() {
		EmployeeMapperAnnotation mapper = session.getMapper(EmployeeMapperAnnotation.class);
		Employee employee = mapper.getEmpById(1);
		System.out.println(mapper.getClass());
		System.out.println(employee);
	}
	/**
	 * ������ɾ��
	 * 1��mybatis������ɾ��ֱ�Ӷ����������ͷ���ֵ
	 * 		Integer��Long��Boolean��void
	 * 2����Ҫ�ֶ��ύ����
	 * 		sqlSessionFactory.openSession();===���ֶ��ύ
	 * 		sqlSessionFactory.openSession(true);===���Զ��ύ
	 * @throws IOException 
	 */
	@Test
	public void test3() {
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		//�������
		Employee employee=new Employee(null, "Jerry", "jerry@163.com", "0");
		mapper.addEmp(employee);
		System.out.println(employee.getId());
	
		//���Ը���
		//Employee employee=new Employee(1, "Jerry", "jerry@163.com", "0");
		//mapper.updateEmp(employee);
		
		 //ɾ��
		//mapper.deleteEmp(2);
		System.out.println("has commited");
	}
	@Test
	public void test4() {
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		Map<String, Object> map=new HashMap<>();
		map.put("id", 1);
		map.put("lastName", "Jerry");
		Employee employee=mapper.getEmpByMap(map);
		System.out.println(employee);
	}
}
