import java.sql.*;
import java.util.Scanner;

public class stuMangerSystem {
    //查询所有学生
    public static void upgradeOneStu(int id1,String name1,String sex1,int classNo1,Statement statement,Connection connection) throws SQLException, ClassNotFoundException {

        //执行sql
        String sql1="UPDATE `student` SET `name`='"+name1+"',`sex`='"+sex1+"' WHERE id='"+id1+"'";
        String sql2="UPDATE `class` SET `classNo`='"+classNo1+"'WHERE studentId='"+id1+"'";
        try{
            connection.setAutoCommit(false);
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
            connection.commit();
            System.out.println("成功！");
        }
        catch (SQLException e1) {
            System.out.println("失败！");
            try {
                connection.rollback();
            } catch (SQLException e2){
                e1.printStackTrace();
            }
            e1.printStackTrace();
        }
    }
    public static void deleteOneStu(int id1,Statement statement,Connection connection) throws SQLException, ClassNotFoundException {

        //执行sql
        String sql1="DELETE FROM `student` WHERE id="+id1;
        String sql2="DELETE FROM `class` WHERE studentId="+id1;
        try{
            connection.setAutoCommit(false);
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
            connection.commit();
            System.out.println("成功！");
        }
        catch (SQLException e1) {
            System.out.println("失败！");
            try {
                connection.rollback();
            } catch (SQLException e2){
                e1.printStackTrace();
            }
            e1.printStackTrace();
        }

    }
    public static void selectAllStu(Statement statement) throws SQLException, ClassNotFoundException {

        //执行sql
        String sql1="SELECT `id`,`name`,`sex`,`classNo`,`entrytime` FROM student INNER JOIN class WHERE id=studentId";
        ResultSet resultSet=statement.executeQuery(sql1);
        while(resultSet.next())
        {
            System.out.print("id:"+resultSet.getObject("id")+"    ");
            System.out.print("name:"+resultSet.getObject("name")+"    ");
            System.out.print("sex:"+resultSet.getObject("sex")+"    ");
            System.out.print("classNo:"+resultSet.getObject("classNo")+"    ");
            System.out.println("entryTime:"+resultSet.getObject("entryTime"));

        }
    }
    public static void selectOneStu(Statement statement,int id) throws SQLException, ClassNotFoundException {

       String sql1="SELECT `id`,`name`,`sex`,`classNo`,`entrytime` FROM student INNER JOIN class WHERE id='"+id+"'AND id=studentId ";
       ResultSet resultSet=statement.executeQuery(sql1);
        while(resultSet.next())
        {
            System.out.print("id:"+resultSet.getObject("id")+"    ");
            System.out.print("name:"+resultSet.getObject("name")+"    ");
            System.out.print("sex:"+resultSet.getObject("sex")+"    ");
            System.out.print("classNo:"+resultSet.getObject("classNo")+"    ");
            System.out.println("entryTime:"+resultSet.getObject("entryTime"));}
    }
    public static void addStu(String name1,String sex1,int classNo1,int id1,Statement statement,Connection connection)throws SQLException, ClassNotFoundException {

        //执行sql
        String sql1 = "INSERT INTO school1.student(`name`,`sex`,`id`) VALUES('" + name1 + "','" + sex1 + "','" + id1 + "')";
        String sql2 = "INSERT INTO school1.class(`classNo`,`entryTime`,`studentId`) VALUES('" + classNo1 + "',CURRENT_TIME,'" + id1 + "')";
        try{
            connection.setAutoCommit(false);
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
            connection.commit();
            System.out.println("成功！");
        }
        catch (SQLException e1) {
            System.out.println("失败！");
            try {
                connection.rollback();
            } catch (SQLException e2){
                e1.printStackTrace();
        }
            e1.printStackTrace();
        }
    }
public static void main(String []args) throws SQLException, ClassNotFoundException {
        Scanner scanner=new Scanner(System.in);
    //加载驱动
    Class.forName("com.mysql.cj.jdbc.Driver");
    //用户名和驱动
    String url="jdbc:mysql://localhost:3306/school1?useUnicode=true&characterEncoding=utf8&useSSL=false";
    String useName="zzz";
    String passWord="123456";
    //连接成功，数据库对象
    Connection connection= DriverManager.getConnection(url,useName,passWord);
    //执行sql的对象
    Statement statement=connection.createStatement();
    //执行sql
        while(true)
        {

        System.out.println("============================================================================");
        System.out.println("功能表如下：\n1.查询所有学生\n2.查询某个学生\n3.增加一名新学生\n4.删除一名学生\n5.修改一名学生信息\n0.退出系统");
        System.out.println("============================================================================");
        System.out.println("请输入你的选择");
            int n=scanner.nextInt();
        switch(n)
        {
            case 0: System.out.println("欢迎下次使用\n============================================================================");
            statement.close();
            connection.close();
            return;
            case 1:System.out.println("全部学生信息显示如下");
            selectAllStu(statement);break;
            case 2:
                System.out.println("请输入你要查询学生的学号:");
                int id=scanner.nextInt();
                System.out.println("查询结果如下");
                selectOneStu(statement,id);
                break;
            case 3:
                System.out.println("请输入你要添加学生的姓名:");
                String name1=scanner.next();
                System.out.println("请输入你要添加学生的性别:");
                String sex1=scanner.next();
                System.out.println("请输入你要添加学生的班级:");
                int classNo1=scanner.nextInt();
                System.out.println("请输入你要添加学生的编号:");
                int id1=scanner.nextInt();
                addStu(name1,sex1,classNo1,id1,statement,connection);
                break;
            case 4:
                System.out.println("请输入你要删除学生的编号:");
                int id2=scanner.nextInt();
                deleteOneStu(id2,statement,connection);
                break;
            case 5:
                System.out.println("请输入你要修改的学生的编号:");
                int id3=scanner.nextInt();
                System.out.println("请输入你要修改后学生的姓名:");
                String name3=scanner.next();
                System.out.println("请输入你要修改后学生的性别:");
                String sex3=scanner.next();
                System.out.println("请输入你要修改后学生的班级:");
                int classNo3=scanner.nextInt();
                upgradeOneStu(id3,name3,sex3,classNo3,statement,connection);

        }
        }
    }

}
