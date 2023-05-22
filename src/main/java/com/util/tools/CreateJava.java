package com.util.tools;

import com.util.utils.MyTool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CreateJava {
    private String packageName;
    private String packageUrl;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setPackageUrl() {
        this.packageUrl = System.getProperty("user.dir") + "\\src\\main\\java\\com\\" + packageName;
    }

    public void setPackageUrl(String packageUrl) {
        this.packageUrl = packageUrl;
    }

    public String getPackageUrl() {
        return packageUrl;
    }

    public static void main(String[] args) {
        CreateJava createJava = new CreateJava();
        createJava.Start();
    }

    /**
     * 开始
     */
    public void Start(){
        setPackageName("banlv");
        setPackageUrl();
//        createPackage();
        // 初始化bean中的类
        List<String> beanList = getBeanList();
        for (String s : beanList) {
            if (s.equals("scenicZoneType")){
                createDao(s);
                createDaoMapper(s);
                createService(s);
                createWebServlet(s);
//                createHtml(s);
//                createJs(s);
                createAttributeSql(s);
//                createHtmlDoor(s);
//                createJSDoor(s);
            }
        }
        createTypeAliases();
    }

    /**
     * 创建文件夹
     */
    private void createPackage() {
        if (packageUrl != "" && packageUrl != null) {
            File file = new File(packageUrl);
            if (file == null){
                if (file.mkdir()){
                    createChildPackage();
                }else {
                    System.out.println("出错了,父文件夹创建失败！");
                }
            }else{
                createChildPackage();
            }
        }
    }

    private void createChildPackage() {
        File beanPackage = new File(packageUrl + "\\bean");
        if (beanPackage.mkdir()) System.out.println("bean文件夹创建成功");
        else System.out.println("bean文件夹创建失败");

        File daoPackage = new File(packageUrl + "\\dao");
        if (daoPackage.mkdir()) System.out.println("dao文件夹创建成功");
        else System.out.println("dao文件夹创建失败");

        File daoMapperFatherPackage = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\com\\"+packageName);
        if (daoMapperFatherPackage.mkdir()) System.out.println("daoMapperFather父文件夹创建成功");
        else System.out.println("daoMapper父文件夹创建失败");
        File daoMapperPackage = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\com\\"+packageName+"\\dao");
        if (daoMapperPackage.mkdir()) System.out.println("daoMapper文件夹创建成功");
        else System.out.println("daoMapper文件夹创建失败");


        File servicePackage = new File(packageUrl + "\\service");
        if (servicePackage.mkdir()){
            System.out.println("service文件夹创建成功");

            File serviceImpPackage = new File(packageUrl + "\\service\\impl");
            if (serviceImpPackage.mkdir()) System.out.println("impl文件夹创建成功");
            else System.out.println("impl文件夹创建失败");
        }
        else System.out.println("service文件夹创建失败");

        File webPackage = new File(packageUrl + "\\web");
        if (webPackage.mkdir()){
            System.out.println("web文件夹创建成功");

            File webServletPackage = new File(packageUrl + "\\web\\servlet");
            if (webServletPackage.mkdir()) System.out.println("servlet文件夹创建成功");
            else System.out.println("servlet文件夹创建失败");
        }
        else System.out.println("web文件夹创建失败");
    }

    /**
     * 获取所有的bean对象
     */
    private List<String> getBeanList() {
        ArrayList<String> beanList = new ArrayList<>();
        if (packageUrl != "" && packageUrl != null) {
            File file = new File(packageUrl + "\\bean");
            File[] fs = file.listFiles();
            for (File f : fs) {
//                if (f.isDirectory())    //若是目录，则递归打印该目录下的文件
//                    func(f);
                if (f.isFile())        //若是文件，直接打印
                {
                    String beanName = f.getName();
                    int length = beanName.length();
                    beanList.add(MyTool.firstLetterNameLower(beanName.substring(0,length-5)));
                }
            }
        }
        return beanList;
    }

    /**
     * 创建Dao层文件
     */
    private void createDao(String beanName){
        if (packageUrl != "" && packageUrl != null) {
            String upperBeanName = MyTool.firstLetterNameUpper(beanName);
            File file = new File(packageUrl + "\\dao\\I" + upperBeanName + "Dao.java");
            try {
                if(file.createNewFile()){
                    System.out.println("dao文件创建成功！");
//                    写入文件
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    String daotext = "package com."+packageName+".dao;\n" +
                            "\n" +
                            "import com."+packageName+".bean."+upperBeanName+";\n" +
                            "\n" +
                            "import java.util.List;\n" +
                            "import java.util.Map;\n" +
                            "\n" +
                            "public interface I"+upperBeanName+"Dao {\n" +
                            "    /**\n" +
                            "     * 查询所有\n" +
                            "     * @return\n" +
                            "     */\n" +
                            "    List<"+upperBeanName+"> findAll();\n" +
                            "\n" +
                            "    /**\n" +
                            "     * 通过页面查询所有\n" +
                            "     * @return\n" +
                            "     */\n" +
                            "    List<"+upperBeanName+"> findAllByPage(Map<String, Integer> pageMap);\n" +
                            "\n" +
                            "    /**\n" +
                            "     * 查询\n" +
                            "     * @return\n" +
                            "     */\n" +
                            "    List<"+upperBeanName+"> searchAllByPage(Map<String, Integer> pageMap, "+upperBeanName+" "+beanName+");\n" +
                            "\n" +
                            "    /**\n" +
                            "     * 查询\n" +
                            "     * @return\n" +
                            "     */\n" +
                            "    List<"+upperBeanName+"> searchAll("+upperBeanName+" "+beanName+");\n" +
                            "\n" +
                            "    /**\n" +
                            "     * 查询总记录数\n" +
                            "     * @return\n" +
                            "     */\n" +
                            "    int findTotalCount();\n" +
                            "\n" +
                            "    /**\n" +
                            "     * 查询总记录数\n" +
                            "     * @return\n" +
                            "     */\n" +
                            "    int findSearchTotalCount("+upperBeanName+" "+beanName+");\n" +
                            "\n" +
                            "    /**\n" +
                            "     * 新增\n" +
                            "     * @return\n" +
                            "     */\n" +
                            "    int add"+upperBeanName+"("+upperBeanName+" "+beanName+");\n" +
                            "\n" +
                            "    /**\n" +
                            "     * 通过Id删除\n" +
                            "     * @return\n" +
                            "     */\n" +
                            "    int deleteBy"+upperBeanName+"Id(int "+beanName+"_id);\n" +
                            "\n" +
                            "    /**\n" +
                            "     * 更新\n" +
                            "     * @return\n" +
                            "     */\n" +
                            "    int update"+upperBeanName+"("+upperBeanName+" "+beanName+");\n" +
                            "}\n";
                    bufferedWriter.write(daotext);
                    bufferedWriter.close();
                }
                else
                    System.out.println("出错了，该dao文件已经存在。" + "bean为：" + beanName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建Dao层映射
     */
    private void createDaoMapper(String beanName){
        if (packageUrl != "" && packageUrl != null) {
            String upperBeanName = MyTool.firstLetterNameUpper(beanName);
            File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\com\\"+packageName+"\\dao\\I" + upperBeanName + "Dao.xml");
            file.delete();
            System.out.println(file);
            try {
                if(file.createNewFile()){
                    System.out.println("文件创建成功！");
//                    写入文件
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

//                    String tableName = packageName + "_" + beanName;
                    String tableName = beanName;
                    if (beanName.equals(packageName)) tableName = packageName;

                    // 通过类名获取属性
                    ClassLoader classLoader = CreateJava.class.getClassLoader();
                    Class<?> beanClass = classLoader.loadClass("com."+packageName+".bean."+upperBeanName);
                    Field[] declaredFields = beanClass.getDeclaredFields();
                    List<String> FieldName = new ArrayList<>();
                    List<String> FieldType = new ArrayList<>();
                    for (Field declaredField : declaredFields) {
                        FieldName.add(declaredField.getName());
                        FieldType.add(declaredField.getGenericType().toString());
                    }

                    String beanId = beanName + "_id";

                    String daoMappertext = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                            "<!DOCTYPE mapper\n" +
                            "        PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n" +
                            "        \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
                            "<!--mapper约束-->\n" +
                            "<mapper namespace=\"com."+packageName+".dao.I"+upperBeanName+"Dao\">\n" +
                            "\n" +
                            "    <!--配置查询所有-->\n" +
                            "    <select id=\"findAll\" resultType=\""+beanName+"\">\n" +
                            "        select * from "+tableName+";\n" +
                            "    </select>\n" +
                            "\n" +
                            "    <!--配置通过页面查询所有-->\n" +
                            "    <select id=\"findAllByPage\" parameterType=\"map\" resultType=\""+beanName+"\">\n" +
                            "        select * from "+tableName+" limit #{start},#{rows};\n" +
                            "    </select>\n" +
                            "\n" +
                            "    <!--通过页面和查询条件查询所有-->\n" +
                            "    <select id=\"searchAllByPage\" resultType=\""+beanName+"\">\n" +
                            "        select * from "+tableName+"\n" +
                            "        where 1 = 1\n";
                    String teststring = "";
                    for (int i = 0; i < FieldName.size(); i++) {
                        teststring += "        <if test=\"arg1."+FieldName.get(i)+" !=null";
                        switch(FieldType.get(i)){
                            case "int":
                                // 判断是否为主外键
                                if("id".equals(FieldName.get(i).substring(FieldName.get(i).lastIndexOf("_") + 1))){
                                    teststring += " and arg1."+FieldName.get(i)+" != 0\">\n";
                                }else {
                                    teststring += " and arg1."+FieldName.get(i)+" != ''\">\n";
                                }
                                break;
                            case "long":
                                // 判断是否为主外键
                                if("id".equals(FieldName.get(i).substring(FieldName.get(i).lastIndexOf("_") + 1))){
                                    teststring += " and arg1."+FieldName.get(i)+" != 0\">\n";
                                }else {
                                    teststring += " and arg1."+FieldName.get(i)+" != ''\">\n";
                                }
                                break;
                            case "double":teststring += " and arg1."+FieldName.get(i)+" != ''\">\n";break;
                            case "float":teststring += " and arg1."+FieldName.get(i)+" != ''\">\n";break;
                            case "class java.lang.String":teststring += " and arg1."+FieldName.get(i)+" !=''\">\n";break;
                            case "class java.sql.Timestamp":teststring += "\">\n";break;
                            case "boolean":teststring += "\">\n";break;
                            default:teststring += "\">\n";break;
                        }
                        teststring +=        "            and "+FieldName.get(i)+"=#{arg1."+FieldName.get(i)+"}\n" +
                                "        </if>\n" ;
                    }
                    daoMappertext += teststring;
                    daoMappertext +=
                            "        limit #{arg0.start},#{arg0.rows};\n" +
                                    "    </select>\n" +
                                    "\n";
                    daoMappertext +=
                            "    <!--通过查询条件查询所有-->\n" +
                                    "    <select id=\"searchAll\" resultType=\""+beanName+"\">\n" +
                                    "        select * from "+tableName+"\n" +
                                    "        where 1 = 1\n";
                    for (int i = 0; i < FieldName.size(); i++) {
                        daoMappertext += "        <if test=\""+FieldName.get(i)+" !=null";
                        switch(FieldType.get(i)){
                            case "int":
                                // 判断是否为主外键
                                if("id".equals(FieldName.get(i).substring(FieldName.get(i).lastIndexOf("_") + 1))){
                                    daoMappertext += " and "+FieldName.get(i)+" != 0\">\n";
                                }else {
                                    daoMappertext += " and "+FieldName.get(i)+" != ''\">\n";
                                }
                                break;
                            case "long":
                                // 判断是否为主外键
                                if("id".equals(FieldName.get(i).substring(FieldName.get(i).lastIndexOf("_") + 1))){
                                    daoMappertext += " and "+FieldName.get(i)+" != 0\">\n";
                                }else {
                                    daoMappertext += " and "+FieldName.get(i)+" != ''\">\n";
                                }
                                break;
                            case "double":daoMappertext += " and "+FieldName.get(i)+" != ''\">\n";break;
                            case "float":daoMappertext += " and "+FieldName.get(i)+" != ''\">\n";break;
                            case "class java.lang.String":daoMappertext += " and "+FieldName.get(i)+" !=''\">\n";break;
                            case "class java.sql.Timestamp":daoMappertext += "\">\n";break;
                            case "boolean":daoMappertext += "\">\n";break;
                            default:daoMappertext += "\">\n";break;
                        }
                        daoMappertext +=        "            and "+FieldName.get(i)+"=#{"+FieldName.get(i)+"}\n" +
                                "        </if>\n" ;
                    }
                    daoMappertext += "\t;";
                    daoMappertext +=
                            "    </select>\n" +
                                    "\n";
                    daoMappertext +=
                            "    <!--查询总记录数-->\n" +
                                    "    <select id=\"findTotalCount\" resultType=\"int\">\n" +
                                    "        select count(*) from "+tableName+";\n" +
                                    "    </select>\n" +
                                    "\n" +
                                    "    <!--查询高级查询记录数-->\n" +
                                    "    <select id=\"findSearchTotalCount\" parameterType=\""+beanName+"\" resultType=\"int\">\n" +
                                    "        select count(*) from "+tableName+"\n" +
                                    "        where 1 = 1\n";

                    for (int i = 0; i < FieldName.size(); i++) {
                        daoMappertext += "        <if test=\""+FieldName.get(i)+"!=null";
                        switch(FieldType.get(i)){
                            case "int":
                                // 判断是否为主外键
                                if("id".equals(FieldName.get(i).substring(FieldName.get(i).lastIndexOf("_") + 1))){
                                    daoMappertext += " and "+FieldName.get(i)+" != 0\">\n";
                                }else {
                                    daoMappertext += " and "+FieldName.get(i)+" != ''\">\n";
                                }
                                break;
                            case "long":
                                // 判断是否为主外键
                                if("id".equals(FieldName.get(i).substring(FieldName.get(i).lastIndexOf("_") + 1))){
                                    daoMappertext += " and "+FieldName.get(i)+" != 0\">\n";
                                }else {
                                    daoMappertext += " and "+FieldName.get(i)+" != ''\">\n";
                                }
                                break;
                            case "double":daoMappertext += " and "+FieldName.get(i)+" != ''\">\n";break;
                            case "float":daoMappertext += " and "+FieldName.get(i)+" != ''\">\n";break;
                            case "class java.lang.String":daoMappertext += " and "+FieldName.get(i)+" !=''\">\n";break;
                            case "class java.sql.Timestamp":daoMappertext += "\">\n";break;
                            case "boolean":daoMappertext += "\">\n";break;
                            default:daoMappertext += "\">\n";break;
                        }
                        daoMappertext +=        "            and "+FieldName.get(i)+"=#{"+FieldName.get(i)+"}\n" +
                                "        </if>\n" ;
                    }

                    daoMappertext += "\t;";

                    daoMappertext +=
                            "    </select>\n" +
                                    "\n" +
                                    "    <!--新增-->\n" +
                                    "    <insert id=\"add"+upperBeanName+"\" parameterType=\""+beanName+"\">\n" +
                                    "        insert into "+tableName+" values (null" ;

                    for (int i = 0; i < FieldName.size(); i++) {
                        if (!FieldName.get(i).equals(beanId)){
                            daoMappertext +=
                                    ",#{"+FieldName.get(i)+"}";
                        }
                    }
                    daoMappertext += ");\n";

                    daoMappertext +=
                            "    </insert>\n" +
                                    "\n" +
                                    "    <!--删除-->\n" +
                                    "    <delete id=\"deleteBy"+upperBeanName+"Id\">\n" +
                                    "        delete from "+tableName+" where "+beanName+"_id = #{"+beanName+"_id};\n" +
                                    "    </delete>\n" +
                                    "\n" +
                                    "    <!--更新-->\n" +
                                    "    <update id=\"update"+upperBeanName+"\" parameterType=\""+beanName+"\">\n" +
                                    "        update "+tableName+"\n" +
                                    "        <set>\n";

                    for (int i = 0; i < FieldName.size(); i++) {
                        daoMappertext += "            <if test=\""+FieldName.get(i)+"!=null";
                        switch(FieldType.get(i)){
                            case "int":daoMappertext += " and "+FieldName.get(i)+" != ''\">\n";break;
                            case "double":daoMappertext += " and "+FieldName.get(i)+" != ''\">\n";break;
                            case "class java.lang.String":daoMappertext += " and "+FieldName.get(i)+"!=''\">\n";break;
                            case "class java.sql.Timestamp":daoMappertext += "\">\n";break;
                            case "boolean":daoMappertext += "\">\n";break;
                            default:daoMappertext += "\">\n";break;
                        }
                        daoMappertext +=        "                "+FieldName.get(i)+"=#{"+FieldName.get(i)+"},\n" +
                                "            </if>\n" ;
                    }

                    daoMappertext +=
                            "        </set>\n" +
                                    "        where "+beanName+"_id=#{"+beanName+"_id};\n" +
                                    "    </update>\n" +
                                    "</mapper>\n";
                    bufferedWriter.write(daoMappertext);
                    bufferedWriter.close();
                }
                else
                    System.out.println("出错了，该daoMapper文件已经存在。" + "bean为：" + beanName);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建Service层文件
     */
    private void createService(String beanName){
        if (packageUrl != "" && packageUrl != null) {
            String upperBeanName = MyTool.firstLetterNameUpper(beanName);
            File file = new File(packageUrl + "\\service\\" + upperBeanName + "Service.java");
            try {
                if(file.createNewFile()){
                    System.out.println("文件创建成功！");
//                    写入文件
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    String serviceText = "package com."+packageName+".service;\n" +
                            "\n" +
                            "import com."+packageName+".bean."+upperBeanName+";\n" +
                            "import com.util.bean.PageBean;\n" +
                            "\n" +
                            "import java.util.List;\n" +
                            "\n" +
                            "public interface "+upperBeanName+"Service {\n" +
                            "    /**\n" +
                            "     * 查询所有\n" +
                            "     * @return\n" +
                            "     */\n" +
                            "    List<"+upperBeanName+"> findAll();\n" +
                            "\n" +
                            "    /**\n" +
                            "     * 通过页面查询所有\n" +
                            "     * @return\n" +
                            "     */\n" +
                            "    PageBean<"+upperBeanName+"> findAllByPage(int currentPage, int rows);\n" +
                            "\n" +
                            "    /**\n" +
                            "     * 通过页面查询所有(空参)\n" +
                            "     * @return\n" +
                            "     */\n" +
                            "    PageBean<"+upperBeanName+"> findAllByPage();\n" +
                            "\n" +
                            "    /**\n" +
                            "     * 查询\n" +
                            "     * @return\n" +
                            "     */\n" +
                            "    PageBean<"+upperBeanName+"> searchAllByPage(int currentPage, int rows,"+upperBeanName+" "+beanName+");\n" +
                            "\n" +
                            "    /**\n" +
                            "     * 查询所有\n" +
                            "     * @return\n" +
                            "     */\n" +
                            "    List<"+upperBeanName+"> searchAll("+upperBeanName+" "+beanName+");\n" +
                            "\n" +
                            "    /**\n" +
                            "     * 新增\n" +
                            "     * @return\n" +
                            "     */\n" +
                            "    int add"+upperBeanName+"("+upperBeanName+" "+beanName+");\n" +
                            "\n" +
                            "    /**\n" +
                            "     * 通过Id删除\n" +
                            "     * @return\n" +
                            "     */\n" +
                            "    int deleteBy"+upperBeanName+"Id(int "+beanName+"Id);\n" +
                            "\n" +
                            "    /**\n" +
                            "     * 更新\n" +
                            "     * @return\n" +
                            "     */\n" +
                            "    int update"+upperBeanName+"("+upperBeanName+" "+beanName+");\n" +
                            "}\n";
                    bufferedWriter.write(serviceText);
                    bufferedWriter.close();

//                    继承类impl
                    File implfile = new File(packageUrl + "\\service\\impl\\" + upperBeanName + "ServiceImpl.java");
                    try {
                        if (implfile.createNewFile()) {
                            System.out.println("Impl文件创建成功！");

                            FileWriter implfileWriter = new FileWriter(implfile);
                            BufferedWriter implbufferedWriter = new BufferedWriter(implfileWriter);
                            String serviceImplText = "package com."+packageName+".service.impl;\n" +
                                    "\n" +
                                    "import com."+packageName+".bean."+upperBeanName+";\n" +
                                    "import com."+packageName+".dao.I"+upperBeanName+"Dao;\n" +
                                    "import com."+packageName+".service."+upperBeanName+"Service;\n" +
                                    "import com.util.bean.PageBean;\n" +
                                    "import com.util.utils.GetSqlSession;\n" +
                                    "\n" +
                                    "import java.util.HashMap;\n" +
                                    "import java.util.List;\n" +
                                    "import java.util.Map;\n" +
                                    "\n" +
                                    "public class "+upperBeanName+"ServiceImpl implements "+upperBeanName+"Service {\n" +
                                    "    private static I"+upperBeanName+"Dao mapper;\n" +
                                    "\n" +
                                    "    static {\n" +
                                    "        mapper = GetSqlSession.safeSqlSession().getMapper(I"+upperBeanName+"Dao.class);\n" +
                                    "    }\n" +
                                    "    @Override\n" +
                                    "    public List<"+upperBeanName+"> findAll() {\n" +
                                    "        return mapper.findAll();\n" +
                                    "    }\n" +
                                    "\n" +
                                    "    @Override\n" +
                                    "    public PageBean<"+upperBeanName+"> findAllByPage(int currentPage, int rows) {\n" +
                                    "//        总记录数\n" +
                                    "        int totalCount = mapper.findTotalCount();\n" +
                                    "        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;\n" +
                                    "        if (currentPage>totalPage) currentPage = totalPage;\n" +
                                    "        if (currentPage<=0) currentPage = 1;\n" +
                                    "//        包装\n" +
                                    "        Map<String, Integer> pageMap = new HashMap<>();\n" +
                                    "        pageMap.put(\"start\",(currentPage-1)*rows);\n" +
                                    "        pageMap.put(\"rows\",rows);\n" +
                                    "//        根据页数查询\n" +
                                    "        List<"+upperBeanName+"> "+beanName+"List = mapper.findAllByPage(pageMap);\n" +
                                    "        return  new PageBean<>(totalCount,totalPage,"+beanName+"List,currentPage,rows);\n" +
                                    "    }\n" +
                                    "\n" +
                                    "\n" +
                                    "    @Override\n" +
                                    "    public PageBean<"+upperBeanName+"> findAllByPage() {\n" +
                                    "        return findAllByPage(0,5);\n" +
                                    "    }\n" +
                                    "\n" +
                                    "    @Override\n" +
                                    "    public PageBean<"+upperBeanName+"> searchAllByPage(int currentPage, int rows, "+upperBeanName+" "+beanName+") {\n" +
                                    "//        总记录数\n" +
                                    "        int totalCount = mapper.findSearchTotalCount("+beanName+");\n" +
                                    "        int totalPage = (totalCount % rows)== 0 ? (totalCount / rows): (totalCount / rows) + 1;\n" +
                                    "        if (currentPage > totalPage) currentPage = totalPage;\n" +
                                    "        if (currentPage<=0) currentPage = 1;\n" +
                                    "\n" +
                                    "//        包装\n" +
                                    "        Map<String, Integer> pageMap = new HashMap<>();\n" +
                                    "        pageMap.put(\"start\",(currentPage-1)*rows);\n" +
                                    "        pageMap.put(\"rows\",rows);\n" +
                                    "//        根据页数查询\n" +
                                    "        List<"+upperBeanName+"> "+beanName+"List = mapper.searchAllByPage(pageMap,"+beanName+");\n" +
                                    "        return  new PageBean<>(totalCount,totalPage,"+beanName+"List,currentPage,rows);\n" +
                                    "    }\n" +
                                    "\n" +
                                    "    @Override\n" +
                                    "    public List<"+upperBeanName+"> searchAll("+upperBeanName+" "+beanName+") {\n" +
                                    "        return mapper.searchAll("+beanName+");\n" +
                                    "    }\n" +
                                    "\n" +
                                    "    @Override\n" +
                                    "    public int add"+upperBeanName+"("+upperBeanName+" "+beanName+") {\n" +
                                    "        int i = mapper.add"+upperBeanName+"("+beanName+");\n" +
                                    "        cleanSqlSession();\n" +
                                    "        return i;\n" +
                                    "    }\n" +
                                    "\n" +
                                    "    @Override\n" +
                                    "    public int deleteBy"+upperBeanName+"Id(int "+beanName+"Id) {\n" +
                                    "        int i = mapper.deleteBy"+upperBeanName+"Id("+beanName+"Id);\n" +
                                    "        cleanSqlSession();\n" +
                                    "        return i;\n" +
                                    "    }\n" +
                                    "\n" +
                                    "    @Override\n" +
                                    "    public int update"+upperBeanName+"("+upperBeanName+" "+beanName+") {\n" +
                                    "        int i = mapper.update"+upperBeanName+"("+beanName+");\n" +
                                    "        cleanSqlSession();\n" +
                                    "        return i;\n" +
                                    "    }\n" +
                                    "\n" +
                                    "    public void cleanSqlSession (){\n" +
                                    "        GetSqlSession.cleanSqlSession();\n" +
                                    "    }\n" +
                                    "\n" +
                                    "}\n";
                            implbufferedWriter.write(serviceImplText);
                            implbufferedWriter.close();
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else
                    System.out.println("出错了，该Service文件已经存在。" + "bean为：" + beanName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建SqlMapconfig.xml中 typeAliases的别名映射
     * 手动复制到<typeAliases>中
     */
    private void createTypeAliases(){
        List<String> beanList = getBeanList();
        String typeTest = "";
        for (int i = 0; i < beanList.size(); i++) {
            typeTest += "<typeAlias type=\"com."+packageName+".bean."+MyTool.firstLetterNameUpper(beanList.get(i))+"\" alias=\""+beanList.get(i)+"\"></typeAlias>\n";
        }
        System.out.println(typeTest);
    }

    /**
     * 创建webservlet文件
     */
    private void createWebServlet(String beanName) {
        if (packageUrl != "" && packageUrl != null) {
            String upperBeanName = MyTool.firstLetterNameUpper(beanName);
            File file = new File(packageUrl + "\\web\\servlet\\" + beanName);
            if (file.mkdir()) {
                System.out.println("webservlet文件夹创建成功！");
            } else System.out.println("出错了，该webservlet文件夹已经存在。" + "bean为：" + beanName);

//            String tableName = packageName + beanName;
            String tableName =  beanName;
            if (beanName.equals(packageName)) tableName = packageName;

            File servletDeleteFile = new File (packageUrl + "\\web\\servlet\\" + beanName + "\\" + upperBeanName + "DeleteServlet.java");
            File servletListFile = new File (packageUrl + "\\web\\servlet\\" + beanName + "\\" + upperBeanName + "ListServlet.java");
            File servletTotalListFile = new File (packageUrl + "\\web\\servlet\\" + beanName + "\\" + upperBeanName + "TotalListServlet.java");
            File servletSearchFile = new File (packageUrl + "\\web\\servlet\\" + beanName + "\\" + upperBeanName + "SearchServlet.java");
            File servletTotalSearchFile = new File (packageUrl + "\\web\\servlet\\" + beanName + "\\" + upperBeanName + "TotalSearchServlet.java");
            File servletSubmitFile = new File (packageUrl + "\\web\\servlet\\" + beanName + "\\" + upperBeanName + "SubmitServlet.java");
            File servletUpdateFile = new File (packageUrl + "\\web\\servlet\\" + beanName + "\\" + upperBeanName + "UpdateServlet.java");

            String servletDeleteText = "package com."+packageName+".web.servlet."+beanName+";\n" +
                    "\n" +
                    "import com.fasterxml.jackson.databind.ObjectMapper;\n" +
                    "import com."+packageName+".service."+upperBeanName+"Service;\n" +
                    "import com."+packageName+".service.impl."+upperBeanName+"ServiceImpl;\n" +
                    "import javax.servlet.ServletException;\n" +
                    "import javax.servlet.annotation.WebServlet;\n" +
                    "import javax.servlet.http.HttpServlet;\n" +
                    "import javax.servlet.http.HttpServletRequest;\n" +
                    "import javax.servlet.http.HttpServletResponse;\n" +
                    "import java.io.IOException;\n" +
                    "import java.util.HashMap;\n" +
                    "import java.util.Map;\n" +
                    "\n" +
                    "@WebServlet(\"/"+tableName+"deleteservlet\")\n" +
                    "public class "+upperBeanName+"DeleteServlet extends HttpServlet {\n" +
                    "    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {\n" +
                    "        request.setCharacterEncoding(\"utf-8\");\n" +
                    "        response.setContentType(\"application/json;charset=utf-8\");\n" +
                    "\n" +
                    "        Map<String, String[]> parameterMap = request.getParameterMap();\n" +
                    "\n" +
                    "        int id = Integer.parseInt(request.getParameter(\"id\"));\n" +
                    "\n" +
                    "        "+upperBeanName+"Service "+beanName+"Service = new "+upperBeanName+"ServiceImpl();\n" +
                    "        int i = "+beanName+"Service.deleteBy"+upperBeanName+"Id(id);\n" +
                    "\n" +
                    "        Map<String,Object> map = new HashMap<>();\n" +
                    "        if (i == 1) map.put(\"message\",true);\n" +
                    "        else map.put(\"message\",false);\n" +
                    "\n" +
                    "        ObjectMapper mapper = new ObjectMapper();\n" +
                    "        mapper.writeValue(response.getWriter(),map);\n" +
                    "    }\n" +
                    "\n" +
                    "    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {\n" +
                    "        this.doPost(request, response);\n" +
                    "    }\n" +
                    "}\n";
            String servletListText = "package com."+packageName+".web.servlet."+beanName+";\n" +
                    "\n" +
                    "import com.fasterxml.jackson.databind.ObjectMapper;\n" +
                    "import com."+packageName+".bean."+upperBeanName+";\n" +
                    "import com."+packageName+".service."+upperBeanName+"Service;\n" +
                    "import com."+packageName+".service.impl."+upperBeanName+"ServiceImpl;\n" +
                    "import com.util.bean.PageBean;\n" +
                    "\n" +
                    "import javax.servlet.ServletException;\n" +
                    "import javax.servlet.annotation.WebServlet;\n" +
                    "import javax.servlet.http.HttpServlet;\n" +
                    "import javax.servlet.http.HttpServletRequest;\n" +
                    "import javax.servlet.http.HttpServletResponse;\n" +
                    "import java.io.IOException;\n" +
                    "import java.util.HashMap;\n" +
                    "import java.util.Map;\n" +
                    "\n" +
                    "@WebServlet(\"/"+tableName+"listservlet\")\n" +
                    "public class "+upperBeanName+"ListServlet  extends HttpServlet {\n" +
                    "    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {\n" +
                    "        request.setCharacterEncoding(\"utf-8\");\n" +
                    "        response.setContentType(\"application/json;charset=utf-8\");\n" +
                    "\n" +
                    "        int currentPage = Integer.parseInt(request.getParameter(\"currentPage\"));\n" +
                    "        int rows = Integer.parseInt(request.getParameter(\"rows\"));\n" +
                    "\n" +
                    "        "+upperBeanName+"Service "+beanName+"Service = new "+upperBeanName+"ServiceImpl();\n" +
                    "        PageBean<"+upperBeanName+"> allByPage = "+beanName+"Service.findAllByPage(currentPage, rows);\n" +
                    "\n" +
                    "        Map<String,Object> map = new HashMap<>();\n" +
                    "        map.put(\"page\",allByPage);\n" +
                    "\n" +
                    "        ObjectMapper mapper = new ObjectMapper();\n" +
                    "        mapper.writeValue(response.getWriter(),map);\n" +
                    "    }\n" +
                    "\n" +
                    "    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {\n" +
                    "        this.doPost(request, response);\n" +
                    "    }\n" +
                    "}\n";
            String servletTotalListText = "package com."+packageName+".web.servlet."+beanName+";\n" +
                    "\n" +
                    "import com.fasterxml.jackson.databind.ObjectMapper;\n" +
                    "import com."+packageName+".bean."+upperBeanName+";\n" +
                    "import com."+packageName+".service."+upperBeanName+"Service;\n" +
                    "import com."+packageName+".service.impl."+upperBeanName+"ServiceImpl;\n" +
                    "\n" +
                    "import javax.servlet.ServletException;\n" +
                    "import javax.servlet.annotation.WebServlet;\n" +
                    "import javax.servlet.http.HttpServlet;\n" +
                    "import javax.servlet.http.HttpServletRequest;\n" +
                    "import javax.servlet.http.HttpServletResponse;\n" +
                    "import java.io.IOException;\n" +
                    "import java.util.HashMap;\n" +
                    "import java.util.List;\n" +
                    "import java.util.Map;\n" +
                    "\n" +
                    "@WebServlet(\"/"+tableName+"totallistservlet\")\n" +
                    "public class "+upperBeanName+"TotalListServlet extends HttpServlet {\n" +
                    "    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {\n" +
                    "        request.setCharacterEncoding(\"utf-8\");\n" +
                    "        response.setContentType(\"application/json;charset=utf-8\");\n" +
                    "\n" +
                    "        "+upperBeanName+"Service "+beanName+"Service = new "+upperBeanName+"ServiceImpl();\n" +
                    "        List<"+upperBeanName+"> "+beanName+"s = "+beanName+"Service.findAll();\n" +
                    "\n" +
                    "        Map<String,Object> map = new HashMap<>();\n" +
                    "        map.put(\""+beanName+"s\","+beanName+"s);\n" +
                    "\n" +
                    "        ObjectMapper mapper = new ObjectMapper();\n" +
                    "        mapper.writeValue(response.getWriter(),map);\n" +
                    "    }\n" +
                    "\n" +
                    "    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {\n" +
                    "        this.doPost(request, response);\n" +
                    "    }\n" +
                    "}\n";
            String servletSearchText = "package com."+packageName+".web.servlet."+beanName+";\n" +
                    "\n" +
                    "import com.fasterxml.jackson.databind.ObjectMapper;\n" +
                    "import com."+packageName+".bean."+upperBeanName+";\n" +
                    "import com."+packageName+".service."+upperBeanName+"Service;\n" +
                    "import com."+packageName+".service.impl."+upperBeanName+"ServiceImpl;\n" +
                    "import com.util.bean.PageBean;\n" +
                    "import com.util.utils.MyTool;\n" +
                    "\n" +
                    "import javax.servlet.ServletException;\n" +
                    "import javax.servlet.annotation.WebServlet;\n" +
                    "import javax.servlet.http.HttpServlet;\n" +
                    "import javax.servlet.http.HttpServletRequest;\n" +
                    "import javax.servlet.http.HttpServletResponse;\n" +
                    "import java.io.IOException;\n" +
                    "import java.util.HashMap;\n" +
                    "import java.util.Map;\n" +
                    "\n" +
                    "@WebServlet(\"/"+tableName+"searchservlet\")\n" +
                    "public class "+upperBeanName+"SearchServlet extends HttpServlet {\n" +
                    "    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {\n" +
                    "        request.setCharacterEncoding(\"utf-8\");\n" +
                    "        response.setContentType(\"application/json;charset=utf-8\");\n" +
                    "\n" +
                    "        int currentPage = Integer.parseInt(request.getParameter(\"currentPage\"));\n" +
                    "        int rows = Integer.parseInt(request.getParameter(\"rows\"));\n" +
                    "\n" +
                    "        Map<String, String[]> parameterMap = request.getParameterMap();\n" +
                    "\n" +
                    "        "+upperBeanName+" "+beanName+" = new "+upperBeanName+"();\n" +
                    "        "+upperBeanName+" "+beanName+"fromWeb = new MyTool<"+upperBeanName+">("+upperBeanName+".class).getObjectFromWeb("+beanName+", parameterMap);\n" +
                    "\n" +
                    "        "+upperBeanName+"Service "+beanName+"Service = new "+upperBeanName+"ServiceImpl();\n" +
                    "        PageBean<"+upperBeanName+"> allByPage = "+beanName+"Service.searchAllByPage(currentPage,rows,"+beanName+"fromWeb);\n" +
                    "\n" +
                    "        Map<String,Object> map = new HashMap<>();\n" +
                    "        map.put(\"page\",allByPage);\n" +
                    "\n" +
                    "        ObjectMapper mapper = new ObjectMapper();\n" +
                    "        mapper.writeValue(response.getWriter(),map);\n" +
                    "    }\n" +
                    "\n" +
                    "    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {\n" +
                    "        this.doPost(request, response);\n" +
                    "    }\n" +
                    "}\n";
            String servletTotalSearchText = "package com."+packageName+".web.servlet."+beanName+";\n" +
                    "\n" +
                    "import com.fasterxml.jackson.databind.ObjectMapper;\n" +
                    "import com.util.utils.MyTool;\n" +
                    "import com."+packageName+".bean."+upperBeanName+";\n" +
                    "import com."+packageName+".service."+upperBeanName+"Service;\n" +
                    "import com."+packageName+".service.impl."+upperBeanName+"ServiceImpl;\n" +
                    "\n" +
                    "import javax.servlet.ServletException;\n" +
                    "import javax.servlet.annotation.WebServlet;\n" +
                    "import javax.servlet.http.HttpServlet;\n" +
                    "import javax.servlet.http.HttpServletRequest;\n" +
                    "import javax.servlet.http.HttpServletResponse;\n" +
                    "import java.io.IOException;\n" +
                    "import java.util.HashMap;\n" +
                    "import java.util.List;\n" +
                    "import java.util.Map;\n" +
                    "\n" +
                    "@WebServlet(\"/"+beanName+"totalsearchservlet\")\n" +
                    "public class "+upperBeanName+"TotalSearchServlet extends HttpServlet {\n" +
                    "    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {\n" +
                    "        request.setCharacterEncoding(\"utf-8\");\n" +
                    "        response.setContentType(\"application/json;charset=utf-8\");\n" +
                    "\n" +
                    "        Map<String, String[]> parameterMap = request.getParameterMap();\n" +
                    "\n" +
                    "        "+upperBeanName+" "+beanName+" = new "+upperBeanName+"();\n" +
                    "        "+upperBeanName+" "+beanName+"fromWeb = new MyTool<"+upperBeanName+">("+upperBeanName+".class).getObjectFromWeb("+beanName+", parameterMap);\n" +
                    "\n" +
                    "        "+upperBeanName+"Service "+beanName+"Service = new "+upperBeanName+"ServiceImpl();\n" +
                    "        List<"+upperBeanName+"> "+beanName+"s = "+beanName+"Service.searchAll("+beanName+"fromWeb);\n" +
                    "\n" +
                    "        Map<String,Object> map = new HashMap<>();\n" +
                    "        map.put(\""+beanName+"s\","+beanName+"s);\n" +
                    "\n" +
                    "        ObjectMapper mapper = new ObjectMapper();\n" +
                    "        mapper.writeValue(response.getWriter(),map);\n" +
                    "    }\n" +
                    "\n" +
                    "    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {\n" +
                    "        this.doPost(request, response);\n" +
                    "    }\n" +
                    "}\n";
            String servletSubmitText = "package com."+packageName+".web.servlet."+beanName+";\n" +
                    "\n" +
                    "import com.fasterxml.jackson.databind.ObjectMapper;\n" +
                    "import com."+packageName+".bean."+upperBeanName+";\n" +
                    "import com."+packageName+".service."+upperBeanName+"Service;\n" +
                    "import com."+packageName+".service.impl."+upperBeanName+"ServiceImpl;\n" +
                    "import com.util.utils.MyTool;\n" +
                    "\n" +
                    "import javax.servlet.ServletException;\n" +
                    "import javax.servlet.annotation.WebServlet;\n" +
                    "import javax.servlet.http.HttpServlet;\n" +
                    "import javax.servlet.http.HttpServletRequest;\n" +
                    "import javax.servlet.http.HttpServletResponse;\n" +
                    "import java.io.IOException;\n" +
                    "import java.util.HashMap;\n" +
                    "import java.util.Map;\n" +
                    "\n" +
                    "@WebServlet(\"/"+tableName+"submitservlet\")\n" +
                    "public class "+upperBeanName+"SubmitServlet extends HttpServlet {\n" +
                    "    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {\n" +
                    "        request.setCharacterEncoding(\"utf-8\");\n" +
                    "        response.setContentType(\"application/json;charset=utf-8\");\n" +
                    "\n" +
                    "        Map<String, String[]> parameterMap = request.getParameterMap();\n" +
                    "\n" +
                    "        "+upperBeanName+" "+beanName+" = new "+upperBeanName+"();\n" +
                    "        "+upperBeanName+" "+beanName+"fromWeb = new MyTool<"+upperBeanName+">("+upperBeanName+".class).getObjectFromWeb("+beanName+", parameterMap);\n" +
                    "\n" +
                    "        "+upperBeanName+"Service "+beanName+"Service = new "+upperBeanName+"ServiceImpl();\n" +
                    "        int i = "+beanName+"Service.add"+upperBeanName+"("+beanName+"fromWeb);\n" +
                    "\n" +
                    "        Map<String,Object> map = new HashMap<>();\n" +
                    "        if (i == 1) map.put(\"message\",true);\n" +
                    "        else map.put(\"message\",false);\n" +
                    "\n" +
                    "        ObjectMapper mapper = new ObjectMapper();\n" +
                    "        mapper.writeValue(response.getWriter(),map);\n" +
                    "    }\n" +
                    "\n" +
                    "    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {\n" +
                    "        this.doPost(request, response);\n" +
                    "    }\n" +
                    "}\n";
            String servletUpdateText = "package com."+packageName+".web.servlet."+beanName+";\n" +
                    "\n" +
                    "import com.fasterxml.jackson.databind.ObjectMapper;\n" +
                    "import com."+packageName+".bean."+upperBeanName+";\n" +
                    "import com."+packageName+".service."+upperBeanName+"Service;\n" +
                    "import com."+packageName+".service.impl."+upperBeanName+"ServiceImpl;\n" +
                    "import com.util.utils.MyTool;\n" +
                    "\n" +
                    "import javax.servlet.ServletException;\n" +
                    "import javax.servlet.annotation.WebServlet;\n" +
                    "import javax.servlet.http.HttpServlet;\n" +
                    "import javax.servlet.http.HttpServletRequest;\n" +
                    "import javax.servlet.http.HttpServletResponse;\n" +
                    "import java.io.IOException;\n" +
                    "import java.util.HashMap;\n" +
                    "import java.util.Map;\n" +
                    "\n" +
                    "@WebServlet(\"/"+tableName+"updateservlet\")\n" +
                    "public class "+upperBeanName+"UpdateServlet extends HttpServlet {\n" +
                    "    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {\n" +
                    "        request.setCharacterEncoding(\"utf-8\");\n" +
                    "        response.setContentType(\"application/json;charset=utf-8\");\n" +
                    "\n" +
                    "        Map<String, String[]> parameterMap = request.getParameterMap();\n" +
                    "\n" +
                    "        "+upperBeanName+" "+beanName+"= new "+upperBeanName+"();\n" +
                    "        "+upperBeanName+" "+beanName+"fromWeb = new MyTool<"+upperBeanName+">("+upperBeanName+".class).getObjectFromWeb("+beanName+", parameterMap);\n" +
                    "\n" +
                    "        "+upperBeanName+"Service "+beanName+"Service = new "+upperBeanName+"ServiceImpl();\n" +
                    "        int i = "+beanName+"Service.update"+upperBeanName+"("+beanName+"fromWeb);\n" +
                    "\n" +
                    "        Map<String,Object> map = new HashMap<>();\n" +
                    "        if (i == 1) map.put(\"message\",true);\n" +
                    "        else map.put(\"message\",false);\n" +
                    "\n" +
                    "        ObjectMapper mapper = new ObjectMapper();\n" +
                    "        mapper.writeValue(response.getWriter(),map);\n" +
                    "    }\n" +
                    "\n" +
                    "    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {\n" +
                    "        this.doPost(request, response);\n" +
                    "    }\n" +
                    "}\n";
            try {
                if(createFile(servletDeleteFile,servletDeleteText)) {
                    System.out.println("文件创建成功！");
                }else {
                    System.out.println("出错了，该servletDelete文件已经存在。bean为：" + beanName);
                }

                if(createFile(servletListFile,servletListText)) {
                    System.out.println("文件创建成功！");
                }else {
                    System.out.println("出错了，该servletList文件已经存在。bean为：" + beanName);
                }

                if(createFile(servletTotalListFile,servletTotalListText)) {
                    System.out.println("文件创建成功！");
                }else {
                    System.out.println("出错了，该servletTotalList文件已经存在。bean为：" + beanName);
                }

                if(createFile(servletSearchFile,servletSearchText)) {
                    System.out.println("文件创建成功！");
                }else {
                    System.out.println("出错了，该servletSearch文件已经存在。bean为：" + beanName);
                }

                if(createFile(servletTotalSearchFile,servletTotalSearchText)) {
                    System.out.println("文件创建成功！");
                }else {
                    System.out.println("出错了，该servletTotalSearch文件已经存在。bean为：" + beanName);
                }

                if(createFile(servletSubmitFile,servletSubmitText)) {
                    System.out.println("文件创建成功！");
                }else {
                    System.out.println("出错了，该servletSubmit文件已经存在。bean为：" + beanName);
                }

                if(createFile(servletUpdateFile,servletUpdateText)) {
                    System.out.println("文件创建成功！");
                }else {
                    System.out.println("出错了，该servletUpdate文件已经存在。bean为：" + beanName);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建html文件
     */
    private void createHtml(String beanName){
        if (packageUrl != "" && packageUrl != null) {
            String upperBeanName = MyTool.firstLetterNameUpper(beanName);
            String htmlpackageName = packageName + "_" + beanName;
            if (beanName.equals(packageName)) htmlpackageName = packageName;
            File file = new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\page\\"+htmlpackageName);
            if (file.mkdir()) {
                System.out.println("html文件夹创建成功！");
            } else System.out.println("出错了，该html文件夹已经存在。" + "bean为：" + beanName);


            File htmlAddFile = new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\page\\"+htmlpackageName + "\\"+ beanName + "add.html");
            File htmlListFile = new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\page\\"+htmlpackageName + "\\"+ beanName + "list.html");
            File htmlUpdateFile = new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\page\\"+htmlpackageName + "\\"+ beanName + "update.html");

            String htmlAddText = "<!DOCTYPE html>\n" +
                    "<!-- 网页使用的语言 -->\n" +
                    "<html lang=\"zh-CN\">\n" +
                    "<head>\n" +
                    "    <!-- 指定字符集 -->\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <!-- 使用Edge最新的浏览器的渲染方式 -->\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <!--\n" +
                    "        viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。\n" +
                    "        width: 默认宽度与设备的宽度相同\n" +
                    "        initial-scale: 初始的缩放比，为1:1 -->\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->\n" +
                    "    <title></title>\n" +
                    "    <!-- 1. 导入CSS的全局样式 -->\n" +
                    "    <link href=\"../../css/bootstrap.css\" rel=\"stylesheet\">\n" +
                    "    <!-- 2. jQuery导入-->\n" +
                    "    <script src=\"../../js/jquery-3.3.1.min.js\"></script>\n" +
                    "    <!-- 3. 导入bootstrap的js文件 -->\n" +
                    "    <script src=\"../../js/bootstrap.js\"></script>\n" +
                    "    <!-- js -->\n" +
                    "    <script src=\"../../js/"+htmlpackageName+"/add.js\"></script>\n" +
                    "    <script src=\"../../js/tool.js\"></script>\n" +
                    "    <!-- css -->\n" +
                    "    <link href=\"../../css/list/main.css\" rel=\"stylesheet\">\n" +
                    "\n" +
                    "    <style type=\"text/css\">\n" +
                    "        td, th {\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "        label{\n" +
                    "            margin: 10px;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "    <script>\n" +
                    "        $(function (){\n" +
                    "            // 初始化\n" +
                    "            init();\n" +
                    "        })\n" +
                    "    </script>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div>\n" +
                    "    <button class=\"btn btn-primary\" onclick=\"returnList()\">返回列表</button>\n" +
                    "</div>" +
                    "<div class=\"container\" id=\"mainContainer\">\n" +
                    "    <table id = \"table\" border=\"1\" class=\"table table-bordered table-hover\">\n" +
                    "        <tr class=\"success\" id=\"tableTr\">\n" +
                    "            <th>字段</th>\n" +
                    "            <th>值</th>\n" +
                    "        </tr>\n" +
                    "    </table>\n" +
                    "    <div id=\"buttondiv\" align=center style=\"\"></div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>\n";
            String htmlListText = "<!DOCTYPE html>\n" +
                    "<!-- 网页使用的语言 -->\n" +
                    "<html lang=\"zh-CN\">\n" +
                    "    <head>\n" +
                    "        <!-- 指定字符集 -->\n" +
                    "        <meta charset=\"utf-8\">\n" +
                    "        <!-- 使用Edge最新的浏览器的渲染方式 -->\n" +
                    "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "        <!--\n" +
                    "            viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。\n" +
                    "            width: 默认宽度与设备的宽度相同\n" +
                    "            initial-scale: 初始的缩放比，为1:1 -->\n" +
                    "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->\n" +
                    "        <title></title>\n" +
                    "        <!-- 1. 导入CSS的全局样式 -->\n" +
                    "        <link href=\"../../css/bootstrap.css\" rel=\"stylesheet\">\n" +
                    "        <!-- 2. jQuery导入-->\n" +
                    "        <script src=\"../../js/jquery-3.3.1.min.js\"></script>\n" +
                    "        <!-- 3. 导入bootstrap的js文件 -->\n" +
                    "        <script src=\"../../js/bootstrap.js\"></script>\n" +
                    "        <!-- js -->\n" +
                    "        <script src=\"../../js/"+htmlpackageName+"/list.js\"></script>\n" +
                    "        <script src=\"../../js/tool.js\"></script>\n" +
                    "        <!-- css -->\n" +
                    "        <link href=\"../../css/list/main.css\" rel=\"stylesheet\">\n" +
                    "\n" +
                    "\n" +
                    "        <script>\n" +
                    "            $(function (){\n" +
                    "                // 初始化\n" +
                    "                init();\n" +
                    "            })\n" +
                    "        </script>\n" +
                    "    </head>\n" +
                    "    <body>\n" +
                    "        <div id=\"mainContainer\" class=\"container border\" style=\"margin-top: 50px\">\n" +
                    "            <div class=\"row border\" style=\"background-color: rgba(0, 147, 221, 0.5);padding-top: 10px;padding-bottom: 10px;\">\n" +
                    "                <div class=\"col-md-6\">\n" +
                    "                    <!-- 高级搜索 -->\n" +
                    "                    <div id='searchform' class=\"row\">\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "                <div id=\"searchbutton\" class=\"col-md-6\">\n" +
                    "                    <div class=\"col-md-2\"></div>\n" +
                    "                    <button id = \"search_button\" class=\"btn btn-success col-md-2\" onclick=\"button_search()\">查询</button>\n" +
                    "                    <div class=\"col-md-4\"></div>\n" +
                    "                    <button type=\"reset\" id = \"reset_button\" class=\"btn btn-warning col-md-2\" onclick=\"search_reset()\">复原</button>\n" +
                    "                    <div class=\"col-md-2\"></div>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "\n" +
                    "            <div class=\"row border\" style=\"background-color: rgba(0, 147, 221, 0.5);\">\n" +
                    "                <span id=\"page_text\" style=\"font-size: 25px;margin-left: 5px;text-align: center;\">\n" +
                    "                    共<text id = \"totalCount\"></text>条记录\n" +
                    "                    每页<text id = \"rows\"></text>条记录\n" +
                    "                    共<text id = \"totalPage\"></text>页\n" +
                    "                    当前第<text id = \"currentPage\"></text>页\n" +
                    "                </span>\n" +
                    "                <nav id=\"turningnav\" aria-label=\"...\">\n" +
                    "                    <ul class=\"pager\">\n" +
                    "                        <li id=\"previous_button\" class=\"previous\"><a href=\"\"><span aria-hidden=\"true\">&larr;</span> 前一页</a></li>\n" +
                    "                        <li id=\"next_button\" class=\"next\"><a href=\"\"> <span aria-hidden=\"true\">&rarr;</span>后一页</a></li>\n" +
                    "                    </ul>\n" +
                    "                </nav>\n" +
                    "            </div>\n" +
                    "\n" +
                    "            <div class=\"row border\" style=\"background-color: rgba(0,147,221,0.2);\">\n" +
                    "                <div class=\"col-md-12\">\n" +
                    "                    <table id = \"table\" border=\"1\" class=\"table table-bordered table-hover\">\n" +
                    "                        <tr></tr>\n" +
                    "                    </table>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "\n" +
                    "            <div class=\"row  border\" style=\"background-color: rgba(0, 147, 221, 0.5);\">\n" +
                    "                <!-- 操作按钮 -->\n" +
                    "                <div id=\"buttondiv\">\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </body>\n" +
                    "</html>\n";
            String htmlUpdateText = "<!DOCTYPE html>\n" +
                    "<!-- 网页使用的语言 -->\n" +
                    "<html lang=\"zh-CN\">\n" +
                    "<head>\n" +
                    "    <!-- 指定字符集 -->\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <!-- 使用Edge最新的浏览器的渲染方式 -->\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <!--\n" +
                    "        viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。\n" +
                    "        width: 默认宽度与设备的宽度相同\n" +
                    "        initial-scale: 初始的缩放比，为1:1 -->\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->\n" +
                    "    <title></title>\n" +
                    "    <!-- 1. 导入CSS的全局样式 -->\n" +
                    "    <link href=\"../../css/bootstrap.css\" rel=\"stylesheet\">\n" +
                    "    <!-- 2. jQuery导入-->\n" +
                    "    <script src=\"../../js/jquery-3.3.1.min.js\"></script>\n" +
                    "    <!-- 3. 导入bootstrap的js文件 -->\n" +
                    "    <script src=\"../../js/bootstrap.js\"></script>\n" +
                    "    <!-- js -->\n" +
                    "    <script src=\"../../js/"+htmlpackageName+"/update.js\"></script>\n" +
                    "    <script src=\"../../js/tool.js\"></script>\n" +
                    "    <!-- css -->\n" +
                    "    <link href=\"../../css/list/main.css\" rel=\"stylesheet\">\n" +
                    "\n" +
                    "    <style type=\"text/css\">\n" +
                    "        td, th {\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "        label{\n" +
                    "            margin: 10px;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "    <script>\n" +
                    "        $(function (){\n" +
                    "            // 初始化\n" +
                    "            init();\n" +
                    "        })\n" +
                    "    </script>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div>\n" +
                    "    <button class=\"btn btn-primary\" onclick=\"returnList()\">返回列表</button>\n" +
                    "</div>" +
                    "<div class=\"container\" id=\"mainContainer\">\n" +
                    "    <table id = \"table\" border=\"1\" class=\"table table-bordered table-hover\">\n" +
                    "        <tr class=\"success\" id=\"tableTr\">\n" +
                    "            <th>字段</th>\n" +
                    "            <th>值</th>\n" +
                    "        </tr>\n" +
                    "    </table>\n" +
                    "    <div id=\"buttondiv\" align=center style=\"\"></div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>\n";
            try {
                if(createFile(htmlAddFile,htmlAddText)) {
                    System.out.println("文件创建成功！");
                }else {
                    System.out.println("出错了，该htmlAdd文件已经存在。bean为：" + beanName);
                }

                if(createFile(htmlListFile,htmlListText)) {
                    System.out.println("文件创建成功！");
                }else {
                    System.out.println("出错了，该htmlList文件已经存在。bean为：" + beanName);
                }

                if(createFile(htmlUpdateFile,htmlUpdateText)) {
                    System.out.println("文件创建成功！");
                }else {
                    System.out.println("出错了，该htmlUpdate文件已经存在。bean为：" + beanName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建js文件
     */
    private void createJs(String beanName){
        if (packageUrl != "" && packageUrl != null) {
            String upperBeanName = MyTool.firstLetterNameUpper(beanName);
            String jspackageName = packageName + "_" + beanName;
            if (beanName.equals(packageName)) jspackageName = packageName;
            File file = new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\js\\"+jspackageName);
            if (file.mkdir()) {
                System.out.println("html文件夹创建成功！");
            } else System.out.println("出错了，该html文件夹已经存在。" + "bean为：" + beanName);


            File jsAddFile = new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\js\\"+jspackageName + "\\add.js");
            File jsListFile = new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\js\\"+jspackageName + "\\list.js");
            File jsUpdateFile = new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\js\\"+jspackageName  + "\\update.js");

            String jsAddText = "var tableName = \"\"\n" +
                    "var zhujian = \"\"\n" +
                    "\n" +
                    "function init(){\n" +
                    "    this.setTableName(\""+beanName+"\")\n" +
                    "    this.initTableTitle('')\n" +
                    "    this.initzhujian('')\n" +
                    "    this.initButton(\"button_reset\",\"重置\",\"button_reset()\")\n" +
                    "    this.initButton(\"button_submit\",\"提交\",\"button_submit()\")\n" +
                    "    this.changeDispalyById('turningnav')\n" +
                    "    this.initAttributeColumn()\n" +
                    "}\n" +
                    "// 设置映射表\n" +
                    "function setTableName(name){\n" +
                    "    tableName = name;\n" +
                    "}\n" +
                    "// 获取映射表\n" +
                    "function getTableName(){\n" +
                    "    return tableName;\n" +
                    "}\n" +
                    "// 初始化页面标题\n" +
                    "function initTitle(titleName) {\n" +
                    "    $('title').html(titleName)\n" +
                    "}\n" +
                    "// 初始化主键信息\n" +
                    "function initzhujian(){\n" +
                    "    var search = window.location.search;\n" +
                    "    // 获取主键与id\n" +
                    "    var index = search.indexOf(\"=\");\n" +
                    "    zhujian = search.substring(index + 1);\n" +
                    "}\n" +
                    "// 隐藏或者显示某模块\n" +
                    "function changeDispalyById(id) {\n" +
                    "    var $id = $(\"#\"+id+\"\");\n" +
                    "    var css = $id.css('display');\n" +
                    "    if (css !== 'none') {\n" +
                    "        $id.css('display','none');\n" +
                    "    } else if (css === 'none') {\n" +
                    "        $id.css('display','inline');\n" +
                    "    } else {\n" +
                    "        $id.css('display','inline');\n" +
                    "    }\n" +
                    "}\n" +
                    "// 初始化表格标题\n" +
                    "function initTableTitle(tablename) {\n" +
                    "    var $mainContainer = $(\"#mainContainer\");\n" +
                    "    // 初始化标题\n" +
                    "    var tableTitle = $(\"<h3 style=\\\"text-align: center\\\">\"+tablename+\"</h3>\");\n" +
                    "    // 标题加入到主容器中\n" +
                    "    $mainContainer.prepend(tableTitle);\n" +
                    "}\n" +
                    "// 初始化按钮\n" +
                    "function initButton(buttonId, buttonText, buttonFuction) {\n" +
                    "    var $buttondiv = $(\"#buttondiv\");\n" +
                    "    // 初始化按钮\n" +
                    "    var button = $(\"<button id=\\\"\"+buttonId+\"\\\" class=\\\"btn btn-primary\\\" onClick=\\\"\"+buttonFuction+\"\\\">\"+buttonText+\"</button>\");\n" +
                    "    // 按钮加入到按钮div中\n" +
                    "    $buttondiv.prepend(button);\n" +
                    "}\n" +
                    "// 获取表格表头信息\n" +
                    "function getTableTr(tableName){\n" +
                    "    var trList;\n" +
                    "    $.ajax({\n" +
                    "        type : \"post\",\n" +
                    "        url : \"../../tabletrservlet\",\n" +
                    "        data : {\n" +
                    "            \"tableName\":tableName\n" +
                    "        },\n" +
                    "        async : false,\n" +
                    "        success : function(data){\n" +
                    "            if (data.exist){\n" +
                    "                trList = data.attributeList;\n" +
                    "            }\n" +
                    "        }\n" +
                    "    });\n" +
                    "    return trList;\n" +
                    "}\n" +
                    "// 初始化输入表单\n" +
                    "function initAttributeColumn() {\n" +
                    "    var trList = getTableTr(getTableName());\n" +
                    "    var $table = $(\"#table\");\n" +
                    "    for (var i = 0; i < trList.length; i++) {\n" +
                    "        // 初始化行\n" +
                    "        var tr = $(\"<tr id='tableTr\" + i + \"'></tr>\");\n" +
                    "        // 添加行至表格末尾\n" +
                    "        $(\"#tableTr\").after(tr);\n" +
                    "        // 初始化td\n" +
                    "        var td1 = $(\"<td>\" + trList[i].attrName + \"</td>\");\n" +
                    "        if (trList[i].attrShow) {\n" +
                    "            var td2 = $(\"<td></td>\");\n" +
                    "        if (trList[i].attrName == zhujian) {\n" +
                    "                var input = $(\"<input id=\" + trList[i].attrValue +\" name = '\"+ trList[i].attrValue +\"' value = \\\"主键 自动生成\\\" class = 'myinput' type=\\\"text\\\" disabled=\\\"disabled\\\">\");;\n" +
                    "            }else {\n" +
                    "                var input = $(\"<input id=\" + trList[i].attrValue +\" name = '\"+ trList[i].attrValue +\"' class = 'myinput' type=\\\"text\\\">\");\n" +
                    "            }\n" +
                    "            td2.append(input)\n" +
                    "        }else{\n" +
                    "            var td2 = $(\"<td></td>\");\n" +
                    "        }\n" +
                    "        // td加入tr中\n" +
                    "        tr.append(td1);\n" +
                    "        tr.append(td2);\n" +
                    "        // div加入到表格中\n" +
                    "        $table.append(tr);\n" +
                    "    }\n" +
                    "}\n" +
                    "// 提交方法\n" +
                    "function button_submit(){\n" +
                    "    var formData = {};\n" +
                    "    $('.myinput').each(function () {\n" +
                    "        var key = $(this).attr(\"name\")\n" +
                    "        var value = $(this).val()\n" +
                    "        formData[key] = value\n" +
                    "    })\n" +
                    "    $.post(\n" +
                    "        \"../../\"+getTableName()+\"submitservlet\",\n" +
                    "        {\n" +
                    "            \"formData\" : formData\n" +
                    "        },\n" +
                    "        // 返回信息处理\n" +
                    "        function (data) {\n" +
                    "            if (data.message == true){\n" +
                    "                alert(\"添加成功！\");\n" +
                    "            } else alert(\"添加失败！\");\n" +
                    "        },\n" +
                    "        \"json\"\n" +
                    "    );\n" +
                    "}\n" +
                    "\n" +
                    "// 重置表单\n" +
                    "function button_reset(){\n" +
                    "    $('input').each(function () {\n" +
                    "        $(this).val(\"\")\n" +
                    "    });\n" +
                    "}\n" +
                    "// 返回列表\n" +
                    "function returnList(){\n" +
                    "    var url = window.location.origin;\n" +
                    "    location.href = url + \"/page/" + packageName + "_" + beanName + "/" + beanName + "list.html\";\n" +
                    "}";
            String jsListText = "var totalCount= 0\n" +
                    "var totalPage = 0\n" +
                    "var currentPage = 0\n" +
                    "var rows = 0\n" +
                    "var tableName = \"\"\n" +
                    "var zhujian = \"\"\n" +
                    "// 页面初始化\n" +
                    "function init(){\n" +
                    "    this.setTableName(\""+beanName+"\")\n" +
                    "    this.initTableTitle('')\n" +
                    "    //this.initSearch('attribute_name','attribute_name','XX')\n" +
                    "    this.initButton(\"button_add\",\"添加\",\"button_add()\")\n" +
                    "    //this.initTitle(\"XX列表\")\n" +
                    "    this,initTableText()\n" +
                    "    this.initTableTr(true)\n" +
                    "    this.getTableData()\n" +
                    "    this.search_check()\n" +
                    "}\n" +
                    "// 设置映射表\n" +
                    "function setTableName(name){\n" +
                    "    tableName = name;\n" +
                    "}\n" +
                    "// 获取映射表\n" +
                    "function getTableName(){\n" +
                    "    return tableName;\n" +
                    "}\n" +
                    "// 初始化页面标题\n" +
                    "function initTitle(titleName) {\n" +
                    "    $('title').html(titleName)\n" +
                    "}\n" +
                    "// 初始化表格标题\n" +
                    "function initTableTitle(tablename) {\n" +
                    "    var $mainContainer = $(\"#mainContainer\");\n" +
                    "    // 初始化标题\n" +
                    "    var tableTitle = $(\"<h3 style=\\\"text-align: center\\\">\"+tablename+\"</h3>\");\n" +
                    "    // 标题加入到主容器中\n" +
                    "    $mainContainer.prepend(tableTitle);\n" +
                    "}\n" +
                    "// 初始化搜索框\n" +
                    "function initSearch(searchId, searchName, searchText) {\n" +
                    "    var $searchform = $(\"#searchform\");\n" +
                    "    // 初始化div\n" +
                    "    var searchdiv = $(\"<div class=\\\"form-group\\\" id=\\\"searchdiv\\\"> </div>\");\n" +
                    "    // 初始化label\n" +
                    "    var searchlabel = $(\"<label for=\\\"exampleInputName\\\">\"+searchText+\"</label>\");\n" +
                    "    // 初始化input\n" +
                    "    var searchinput = $(\"<input type=\\\"text\\\" class=\\\"searchinput\\\" id=\\\"\"+searchId+\"\\\"name=\\\"\"+searchName+\"\\\" value=\\\"\\\">\")\n" +
                    "    // input和label加入到div中\n" +
                    "    searchdiv.prepend(searchlabel,searchinput);\n" +
                    "    // div加入到高级搜索框中\n" +
                    "    $searchform.prepend(searchdiv);\n" +
                    "}\n" +
                    "// 初始化按钮\n" +
                    "function initButton(buttonId, buttonText, buttonFuction) {\n" +
                    "    var $buttondiv = $(\"#buttondiv\");\n" +
                    "    // 初始化按钮\n" +
                    "    var button = $(\"<button id=\\\"\"+buttonId+\"\\\" class=\\\"btn btn-primary\\\" onClick=\\\"\"+buttonFuction+\"\\\">\"+buttonText+\"</button>\");\n" +
                    "    // 按钮加入到按钮div中\n" +
                    "    $buttondiv.prepend(button);\n" +
                    "}\n" +
                    "// 初始化表头\n" +
                    "function initTableTr(ifoperation) {\n" +
                    "    var trList = getTableTr(getTableName());\n" +
                    "    var $table = $(\"#table\");\n" +
                    "    // 获取tr\n" +
                    "    var tr = $(\"#table>tr\");\n" +
                    "    // 初始化tr\n" +
                    "    tr = $(\"<tr class=\\\"success\\\" id=\\\"tableTr\\\"> </tr>\");\n" +
                    "    for (var i = 0; i < trList.length; i++) {\n" +
                    "        if (trList[i].attrShow){\n" +
                    "            // 初始化th\n" +
                    "            var th = $(\"<th>\"+trList[i].attrName+\"</th>\");\n" +
                    "            // th加入tr中\n" +
                    "            tr.append(th);\n" +
                    "        }\n" +
                    "    }\n" +
                    "    if (ifoperation) {\n" +
                    "        // 初始化操作th\n" +
                    "        var th = $(\"<th>操作</th>\");\n" +
                    "        // th加入tr中\n" +
                    "        tr.append(th);\n" +
                    "    }\n" +
                    "    // div加入到表格中\n" +
                    "    $table.append(tr);\n" +
                    "}\n" +
                    "// 获取表格表头信息\n" +
                    "function getTableTr(tableName){\n" +
                    "    var trList;\n" +
                    "    $.ajax({\n" +
                    "        type : \"post\",\n" +
                    "        url : \"../../tabletrservlet\",\n" +
                    "        data : {\n" +
                    "            \"tableName\":tableName\n" +
                    "        },\n" +
                    "        async : false,\n" +
                    "        success : function(data){\n" +
                    "            if (data.exist){\n" +
                    "                trList = data.attributeList;\n" +
                    "            }\n" +
                    "        }\n" +
                    "    });\n" +
                    "    return trList;\n" +
                    "}\n" +
                    "// 初始化表单page信息\n" +
                    "function initTableText(){\n" +
                    "    //获取当前页与每页记录数\n" +
                    "    var search = window.location.search;\n" +
                    "    //获取当前页\n" +
                    "    var index = search.indexOf(\"=\");\n" +
                    "    var search = search.substring(index+1);\n" +
                    "    index = search.indexOf(\"&\");\n" +
                    "    currentPage = search.substring(0,index);\n" +
                    "    //获取当前页行数\n" +
                    "    var index = search.indexOf(\"=\");\n" +
                    "    rows = search.substring(index+1);\n" +
                    "    if (currentPage == \"\") currentPage = 0;\n" +
                    "    if (rows == \"\") rows = 5;\n" +
                    "}\n" +
                    "// 更新表单page信息\n" +
                    "function updateTableText(page){\n" +
                    "    // 获取总数据量\n" +
                    "    totalCount =page.totalCount;\n" +
                    "    // 获取总页数\n" +
                    "    totalPage = page.totalPage;\n" +
                    "    // 设置当前页数\n" +
                    "    currentPage = page.currentPage;\n" +
                    "    // 设置当前每页显示个数\n" +
                    "    rows = page.rows;\n" +
                    "\n" +
                    "    //更新页数\n" +
                    "    $(\"#totalCount\").html(totalCount);\n" +
                    "    $(\"#totalPage\").html(totalPage);\n" +
                    "    $(\"#currentPage\").html(currentPage);\n" +
                    "    $(\"#rows\").html(rows);\n" +
                    "\n" +
                    "    updateTurningnav(page)\n" +
                    "}\n" +
                    "// 更新表单翻页工具\n" +
                    "function updateTurningnav(page){\n" +
                    "    // 获取总页数\n" +
                    "    totalPage = page.totalPage;\n" +
                    "    // 设置当前页数\n" +
                    "    currentPage = page.currentPage;\n" +
                    "    // 获取网址\n" +
                    "    var index = window.location.href.indexOf(\"?\");\n" +
                    "    // 判断是否显示\n" +
                    "    if(currentPage == 1) {\n" +
                    "        dispalyById(\"previous_button\");\n" +
                    "    } else {\n" +
                    "        showById(\"previous_button\");\n" +
                    "        var previousURL = window.location.href.substring(0,index) + \"?currentPage=\" + (currentPage - 1) + \"&rows=\" + rows;\n" +
                    "        $(\"#previous_button > a\").attr(\"href\",previousURL);\n" +
                    "    }\n" +
                    "    if (currentPage == totalPage) {\n" +
                    "        dispalyById(\"next_button\");\n" +
                    "    } else {\n" +
                    "        showById(\"next_button\");\n" +
                    "        var nextURL = window.location.href.substring(0,index) + \"?currentPage=\" + (currentPage + 1) + \"&rows=\" + rows;\n" +
                    "        $(\"#next_button > a\").attr(\"href\",nextURL);\n" +
                    "    }\n" +
                    "}\n" +
                    "// 获取表单数据\n" +
                    "function getTableData(){\n" +
                    "    $.post(\n" +
                    "        \"../../\"+getTableName()+\"listservlet\",\n" +
                    "        {\n" +
                    "            \"currentPage\":currentPage,\n" +
                    "            \"rows\":rows\n" +
                    "        },\n" +
                    "        // 返回信息处理\n" +
                    "        function (data) {\n" +
                    "            // 重置表单\n" +
                    "            resetTable()\n" +
                    "            // 更新表单page信息\n" +
                    "            updateTableText(data.page);\n" +
                    "            // 更新表单信息\n" +
                    "            initTableData(data,tableName);\n" +
                    "        },\n" +
                    "        \"json\"\n" +
                    "    );\n" +
                    "}\n" +
                    "// 初始化表单数据\n" +
                    "function initTableData(tableData,tableName){\n" +
                    "    // 获取表头信息\n" +
                    "    var trList = getTableTr(tableName);\n" +
                    "    // 更新表格\n" +
                    "    var $table = $(\"#table\");\n" +
                    "    // 当前行数\n" +
                    "    var currentrows = window.totalCount - (window.rows * (window.currentPage - 1))\n" +
                    "    // 索引\n" +
                    "    var index = currentrows < window.rows ? currentrows : window.rows;\n" +
                    "    for (var i = 0; i < index; i++) {\n" +
                    "        // 获取行数据\n" +
                    "        var trData = tableData.page.list[i];\n" +
                    "        for (var k = 0; k < trList.length; k++) {\n" +
                    "            if (trList[k].attrValue == tableName + \"_id\") {\n" +
                    "                zhujian = trList[k].attrValue;\n" +
                    "            }\n" +
                    "        }\n" +
                    "        // 初始化行\n" +
                    "        var tr = $(\"<tr id='tableTr\" + $(trData).attr(zhujian) + \"'></tr>\");\n" +
                    "        // 添加行至表格末尾\n" +
                    "        $(\"#tableTr\").after(tr);\n" +
                    "        // 获取表格头\n" +
                    "        for (var j = 0; j < trList.length; j++) {\n" +
                    "            if (trList[j].attrShow) {\n" +
                    "                // 获取标签\n" +
                    "                var tdColumn = trList[j].attrValue;\n" +
                    "                // 初始化td\n" +
                    "                var td = $(\"<td>\" + $(trData).attr(tdColumn) + \"</td>\");\n" +
                    "                // td加入tr中\n" +
                    "                tr.append(td);\n" +
                    "            }\n" +
                    "        }\n" +
                    "        // 操作按钮\n" +
                    "        var operation = $(\"<td></td>\");\n" +
                    "        var updateButton = $(\"<a class=\\\"btn btn-default btn-sm\\\" onclick='button_update(this,event)'>修改</a>\");\n" +
                    "        var deleteButton = $(\"<a class=\\\"btn btn-default btn-sm\\\" onclick='button_delete(this,event)'>删除</a>\");\n" +
                    "\n" +
                    "        operation.append(updateButton, deleteButton);\n" +
                    "\n" +
                    "        tr.append(operation)\n" +
                    "\n" +
                    "        $table.append(tr);\n" +
                    "    }\n" +
                    "}\n" +
                    "// 新增页面跳转\n" +
                    "function button_add() {\n" +
                    "    location.href = getURL()+\"add.html?zhujian=\" + zhujian;\n" +
                    "}\n" +
                    "// 更新方法\n" +
                    "function button_update(a,b) {\n" +
                    "    if (confirm(\"确定修改？\")) {\n" +
                    "        var id = $(a).parent().parent().attr('id');\n" +
                    "        location.href = getURL() + \"update.html?tableName=\" +getTableName()+\"&\"+ zhujian +\"=\" + getId(id);\n" +
                    "    }\n" +
                    "}\n" +
                    "// 删除方法\n" +
                    "function button_delete(a,b) {\n" +
                    "    if (confirm(\"确定删除？\")) {\n" +
                    "        var id = $(a).parent().parent().attr('id');\n" +
                    "        $.post(\n" +
                    "            \"../../\"+getTableName()+\"deleteservlet\",\n" +
                    "            {\n" +
                    "                \"id\" : getId(id)\n" +
                    "            },\n" +
                    "            // 返回信息处理\n" +
                    "            function (data) {\n" +
                    "                if (data.message){\n" +
                    "                    alert(\"删除成功！\");\n" +
                    "                    // 重置表单\n" +
                    "                    resetTable()\n" +
                    "\n" +
                    "                    getTableData();\n" +
                    "                } else alert(\"删除失败！\");\n" +
                    "            },\n" +
                    "            \"json\"\n" +
                    "        );\n" +
                    "    }\n" +
                    "}\n" +
                    "// 查询方法\n" +
                    "function button_search(){\n" +
                    "    var formData = {}\n" +
                    "    $('.searchinput').each(function () {\n" +
                    "        var key = $(this).attr(\"id\")\n" +
                    "        var value = $(this).val()\n" +
                    "        formData[key] = value\n" +
                    "    });\n" +
                    "    $.post(\n" +
                    "        \"../../\"+getTableName()+\"searchservlet\",\n" +
                    "        {\n" +
                    "            \"currentPage\":currentPage,\n" +
                    "            \"rows\":rows,\n" +
                    "            \"formData\" : formData\n" +
                    "        },\n" +
                    "        // 返回信息处理\n" +
                    "        function (data) {\n" +
                    "            // 重置表单\n" +
                    "            resetTable()\n" +
                    "            // 更新表单page信息\n" +
                    "            updateTableText(data.page);\n" +
                    "            // 更新表单信息\n" +
                    "            initTableData(data,tableName);\n" +
                    "        },\n" +
                    "        \"json\"\n" +
                    "    );\n" +
                    "}\n" +
                    "\n" +
                    "// 重置表单\n" +
                    "function resetTable(){\n" +
                    "    $('tr').each(function () {\n" +
                    "        var id = $(this).attr(\"id\")\n" +
                    "        if (id == null) return;\n" +
                    "        if (id.substring(0, 7) == 'tableTr' && id.length > 7) {\n" +
                    "            $(this).remove();\n" +
                    "        }\n" +
                    "    });\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "// 重置查询表单\n" +
                    "function search_reset(){\n" +
                    "    $('#searchdiv > input').each(function () {\n" +
                    "        $(this).val(\"\")\n" +
                    "    });\n" +
                    "    // 空查询\n" +
                    "    button_search()\n" +
                    "}\n" +
                    "\n" +
                    "// 查询表单检测是否为空\n" +
                    "function search_check(){\n" +
                    "    var num = 0;\n" +
                    "    $('#searchform > #searchdiv > input').each(function () {\n" +
                    "        num ++;\n" +
                    "    });\n" +
                    "    if (num == 0){\n" +
                    "        changeDispalyById(\"searchbutton\");\n" +
                    "    }\n" +
                    "}";
            String jsUpdateText = "var tableName = \"\"\n" +
                    "var zhujian = \"\"\n" +
                    "var id = \"\"\n" +
                    "\n" +
                    "function init(){\n" +
                    "    this.setTableName()\n" +
                    "    //this.initTableTitle('更新XX表')\n" +
                    "    this.initButton(\"button_reset\",\"重置\",\"button_reset()\")\n" +
                    "    this.initButton(\"button_submit\",\"提交\",\"button_submit()\")\n" +
                    "    this.changeDispalyById('turningnav')\n" +
                    "    this.initAttributeColumn()\n" +
                    "}\n" +
                    "// 获取表名和主键\n" +
                    "function setTableName(){\n" +
                    "    var search = window.location.search;\n" +
                    "    //获取当前表名\n" +
                    "    var index = search.indexOf(\"=\");\n" +
                    "    var search = search.substring(index+1);\n" +
                    "    index = search.indexOf(\"&\");\n" +
                    "    tableName = search.substring(0,index);\n" +
                    "    // 获取主键与id\n" +
                    "    var index1 = search.indexOf(\"&\");\n" +
                    "    var index2 = search.indexOf(\"=\");\n" +
                    "    zhujian = search.substring(index1 + 1,index2);\n" +
                    "    id = search.substring(index2 + 1);\n" +
                    "}\n" +
                    "// 获取映射表\n" +
                    "function getTableName(){\n" +
                    "    return tableName;\n" +
                    "}\n" +
                    "// 初始化页面标题\n" +
                    "function initTitle(titleName) {\n" +
                    "    $('title').html(titleName)\n" +
                    "}\n" +
                    "// 初始化表格标题\n" +
                    "function initTableTitle(tablename) {\n" +
                    "    var $mainContainer = $(\"#mainContainer\");\n" +
                    "    // 初始化标题\n" +
                    "    var tableTitle = $(\"<h3 style=\\\"text-align: center\\\">\"+tablename+\"</h3>\");\n" +
                    "    // 标题加入到主容器中\n" +
                    "    $mainContainer.prepend(tableTitle);\n" +
                    "}\n" +
                    "// 初始化按钮\n" +
                    "function initButton(buttonId, buttonText, buttonFuction) {\n" +
                    "    var $buttondiv = $(\"#buttondiv\");\n" +
                    "    // 初始化按钮\n" +
                    "    var button = $(\"<button id=\\\"\"+buttonId+\"\\\" class=\\\"btn btn-primary\\\" onClick=\\\"\"+buttonFuction+\"\\\">\"+buttonText+\"</button>\");\n" +
                    "    // 按钮加入到按钮div中\n" +
                    "    $buttondiv.prepend(button);\n" +
                    "}\n" +
                    "// 获取表格表头信息\n" +
                    "function getTableTr(tableName){\n" +
                    "    var trList;\n" +
                    "    $.ajax({\n" +
                    "        type : \"post\",\n" +
                    "        url : \"../../tabletrservlet\",\n" +
                    "        data : {\n" +
                    "            \"tableName\":tableName\n" +
                    "        },\n" +
                    "        async : false,\n" +
                    "        success : function(data){\n" +
                    "            if (data.exist){\n" +
                    "                trList = data.attributeList;\n" +
                    "            }\n" +
                    "        }\n" +
                    "    });\n" +
                    "    return trList;\n" +
                    "}\n" +
                    "// 通过主键获取实体类\n" +
                    "function getBeanById(tableName,id){\n" +
                    "    var formData = {}\n" +
                    "    formData[zhujian] = id\n" +
                    "    var bean = [];\n" +
                    "    $.ajax({\n" +
                    "        type : \"post\",\n" +
                    "        url : \"../../\" + getTableName() +\"searchservlet\",\n" +
                    "        data : {\n" +
                    "            \"currentPage\":1,\n" +
                    "            \"rows\":5,\n" +
                    "            \"formData\" : formData\n" +
                    "        },\n" +
                    "        async : false,\n" +
                    "        success : function(data){\n" +
                    "            if (data.page.list.length > 0){\n" +
                    "                bean = data.page.list[0];\n" +
                    "            }\n" +
                    "        }\n" +
                    "    });\n" +
                    "    return bean;\n" +
                    "}\n" +
                    "// 初始化输入表单\n" +
                    "function initAttributeColumn() {\n" +
                    "    var trList = getTableTr(tableName);\n" +
                    "    var bean = getBeanById(tableName,id)\n" +
                    "    var $table = $(\"#table\");\n" +
                    "    for (var i = 0; i < trList.length; i++) {\n" +
                    "        // 初始化行\n" +
                    "        var tr = $(\"<tr id='tableTr\" + i + \"'></tr>\");\n" +
                    "        // 添加行至表格末尾\n" +
                    "        $(\"#tableTr\").after(tr);\n" +
                    "        // 初始化td\n" +
                    "        var td1 = $(\"<td>\" + trList[i].attrName + \"</td>\");\n" +
                    "        var td2 = $(\"<td></td>\");\n" +
                    "        if (trList[i].attrName == zhujian) {\n" +
                    "            var input = $(\"<input id=\" + trList[i].attrValue +\" name = '\"+ trList[i].attrValue +\"' class = 'myinput' type=\\\"text\\\" disabled=\\\"disabled\\\">\" );\n" +
                    "            var text = $(\"<text\\\">主键 不可修改</text> <br></br>\");\n" +
                    "            td2.append(text);\n" +
                    "        }else {\n" +
                    "            var input = $(\"<input id=\" + trList[i].attrValue +\" name = '\"+ trList[i].attrValue +\"' class = 'myinput' type=\\\"text\\\">\");\n" +
                    "        }\n" +
                    "        input.val($(bean).attr(trList[i].attrValue))\n" +
                    "        if (!trList[i].attrShow) {\n" +
                    "            input.css('display','none');\n" +
                    "            var text = $(\"<text>\" + $(bean).attr(trList[i].attrValue) + \"</text>\");\n" +
                    "            td2.append(text)\n" +
                    "        }\n" +
                    "        td2.append(input)\n" +
                    "        // td加入tr中\n" +
                    "        tr.append(td1);\n" +
                    "        tr.append(td2);\n" +
                    "        // div加入到表格中\n" +
                    "        $table.append(tr);\n" +
                    "    }\n" +
                    "}\n" +
                    "// 提交方法\n" +
                    "function button_submit(){\n" +
                    "    var formData = {};\n" +
                    "    $('.myinput').each(function () {\n" +
                    "        var key = $(this).attr(\"name\")\n" +
                    "        var value = $(this).val()\n" +
                    "        formData[key] = value\n" +
                    "    })\n" +
                    "    $.post(\n" +
                    "        \"../../\"+getTableName()+\"updateservlet\",\n" +
                    "        {\n" +
                    "            \"formData\" : formData\n" +
                    "        },\n" +
                    "        // 返回信息处理\n" +
                    "        function (data) {\n" +
                    "            if (data.message == true){\n" +
                    "                alert(\"更新成功！\");\n" +
                    "            } else alert(\"更新失败！\");\n" +
                    "        },\n" +
                    "        \"json\"\n" +
                    "    );\n" +
                    "}\n" +
                    "\n" +
                    "// 重置表单\n" +
                    "function button_reset(){\n" +
                    "    $('input').each(function () {\n" +
                    "        $(this).val(\"\")\n" +
                    "    });\n" +
                    "}\n" +
                    "// 返回列表\n" +
                    "function returnList(){\n" +
                    "    var url = window.location.origin;\n" +
                    "    location.href = url + \"/page/" + packageName + "_" + beanName + "/" + beanName + "list.html\";\n" +
                    "}";
            try {
                if(createFile(jsAddFile,jsAddText)) {
                    System.out.println("文件创建成功！");
                }else {
                    System.out.println("出错了，该jsAdd文件已经存在。bean为：" + beanName);
                }

                if(createFile(jsListFile,jsListText)) {
                    System.out.println("文件创建成功！");
                }else {
                    System.out.println("出错了，该jsList文件已经存在。bean为：" + beanName);
                }

                if(createFile(jsUpdateFile,jsUpdateText)) {
                    System.out.println("文件创建成功！");
                }else {
                    System.out.println("出错了，该jsUpdate文件已经存在。bean为：" + beanName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 创建文件方法
     */
    private boolean createFile(File file,String text) throws IOException {
        if(file.createNewFile()) {
            System.out.println("文件创建成功！");
//          写入文件
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(text);
            bufferedWriter.close();
            fileWriter.close();
            return true;
        }else {
            return false;
        }
    }

    /**
     * attribute新增数据
     */
    private void createAttributeSql(String beanName){
        try {
            String upperBeanName = MyTool.firstLetterNameUpper(beanName);
            // 通过类名获取属性
            ClassLoader classLoader = CreateJava.class.getClassLoader();
            Class<?> beanClass = classLoader.loadClass("com."+packageName+".bean."+upperBeanName);
            Field[] declaredFields = beanClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.getName();
                declaredField.getGenericType().toString();
                String SQL = "";
                SQL += "INSERT INTO " + packageName + ".attribute (attrId, attrTable, attrValue, attrName, attrShow) VALUES (null,'" +beanName + "','" + declaredField.getName() + "','" + declaredField.getName()  + "','1');";
                System.out.println(SQL);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 门户
     */
    private void createHtmlDoor(String beanName){
        String upper = MyTool.firstLetterNameUpper(beanName);
        String doorButton = "<div>\n" +
                "            <button class=\"btn btn-default\" onclick=\"jump"+upper+"()\">"+beanName+"页面</button>\n" +
                "        </div>";
        System.out.println(doorButton);
    }

    private void createJSDoor(String beanName){
        String upper  = MyTool.firstLetterNameUpper(beanName);
        String functionString  = "function jump"+upper+"(){\n" +
                "            location.href = getURL() + \"page/" + packageName + "_" + beanName + "/" + beanName + "list.html\";\n" +
                "        }";
        System.out.println(functionString);
    }

}

