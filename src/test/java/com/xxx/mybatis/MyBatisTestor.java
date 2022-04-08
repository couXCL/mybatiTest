package com.xxx.mybatis;


import com.xxx.mybatis.dto.GoodsDTO;
import com.xxx.mybatis.entity.Goods;
import com.xxx.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyBatisTestor {
    @Test
    public void testSqlSessionFactory() throws IOException {
        SqlSession sqlSession = null;
        try {
            //利用Reader加载classpath下的mybatis-config.xml核心配置文件
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            //初始化SqlSessionFactory对象，同时解析mybatis-config.xml文件
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            //创建SqlSession对象，SqlSession是JDBC扩展类，用于数据库交互
            sqlSession = sqlSessionFactory.openSession();
            //测试数据库连接（测试用）
            System.out.println(sqlSession.getConnection().toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (sqlSession!=null){
                //如果mybatis-config.xml配置文件中的type=“POOLED”，代表使用连接池，close则是将连接回收到连接池中
                //如果mybatis-config.xml配置文件中的type=“UNPOOLED”,代表直连，close则会调用Connection.close()方法
                sqlSession.close();

            }
        }




    }
    @Test
    public void testMyBatisUtils(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            System.out.println(sqlSession.getConnection().toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
    @Test
    public void testSelectAll(){
        SqlSession sqlSession = null;
        try {
           sqlSession = MyBatisUtils.openSession();
            List<Goods> lists = sqlSession.selectList("goods.selectAll");
            for (Goods good:lists) {
                System.out.println(good.getTitle());
            }
        }catch (Exception e){
            throw e;
        }finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
    @Test
    public void testSelectById(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Goods goods = sqlSession.selectOne("goods.selectById", 3);
            System.out.println(goods.getTitle());
        }catch (Exception e){
            throw e;
        }finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
    @Test
    public void testSelectByPriceRange(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Map<Object,Object> param = new HashMap<Object,Object>();
            param.put("min",1);
            param.put("max",80);
            param.put("limit",10);
            List<Goods> lists = sqlSession.selectList("goods.selectByPriceRange", param);
            for (Goods good:lists) {
                System.out.println(good.getTitle());
            }
        }catch (Exception e){
            throw e;
        }finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
    @Test
    public void testSelectGoodsMap() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            List<Map> lists = sqlSession.selectList("goods.selectGoodsMap");
            for (Map good : lists) {
                System.out.println(good);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testSelectGoodsDTO() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            List<GoodsDTO> lists = sqlSession.selectList("goods.selectGoodsDTO");
            for (GoodsDTO good : lists) {
                System.out.println(good.getGoods().getTitle());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Goods goods = new Goods();
            goods.setTitle("插入测试标题");
            goods.setSubTitle("插入测试副标题");
            goods.setOriginalCost(129.7f);
            goods.setCurrentCost(120.2f);
            goods.setIsFreeDelivery(1);
            goods.setCategoryId(3);
            //insert()方法返回值代表本次成功插入的记录总数
            int num = sqlSession.insert("goods.insert",goods);
            sqlSession.commit();
            //在xml添加selectKey标签后可以直接查到生成的主键
            System.out.println(goods.getGoodsId());

        }catch (Exception e){
            if (sqlSession!=null){
                //回滚事务
                sqlSession.rollback();
            }
            throw e;
        }finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testUpdate() throws Exception{
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            //对于更新操作不建议手动set来组织原始的数据，而是通过selectById之类的方法获取到原始信息，再在原始信息的基础上进行修改再进行更新
            Goods goods = sqlSession.selectOne("goods.selectById",3);
            goods.setTitle("更新商品");
            goods.setIsFreeDelivery(1);
            int num = sqlSession.update("goods.update",goods);
            System.out.println(num);
            sqlSession.commit();
        }catch (Exception e){
            if (sqlSession!=null){
                sqlSession.rollback();
            }
            throw e;
        }finally {
            if (sqlSession!=null){
                MyBatisUtils.closeSession(sqlSession);
            }
        }
    }
    @Test
    public void testDelete(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            sqlSession.delete("goods.delete",4);
            sqlSession.commit();
        }catch (Exception e){
            if (sqlSession!=null){
                sqlSession.rollback();
            }
            throw e;
        }finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
}
