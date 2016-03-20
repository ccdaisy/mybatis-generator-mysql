# mybatis-generator-mysql
mysql plugin for mybatis-generator. mvn prj.

mybatis的自动生成一直都是常用且高效的dao生成方式，自动化就必然会使得出错少、并且易管理。可是很多时候，不同数据库有其不同的语句特征，比如mysql的自增主键的获取、mysql的分页和其它数据库都有所不同，因此加了两个plugin来辅助mybatis-generator生成代码。
## 主要功能：
### InsertSelectKeyPlugin:
    <selectKey keyProperty="iID" resultType="int" order="AFTER">
    select LAST_INSERT_ID()
    </selectKey>
生成固定的mysql返回插入数据的当前自增键
### PaginationPlugin:
生成select的固定limit语句，同时在example增加limit功能。

觉得mysql还有不少可以使用的plugin，如逻辑删除等等，有时间完善再加。

## 使用方法：
###1.加入此工程的dependencies到maven的plugin element。
    <plugins>			
    	 <plugin>				
    		  <groupId>org.mybatis.generator</groupId>				
    		  <artifactId>mybatis-generator-maven-plugin</artifactId>					  
    		  <version>1.3.2</version>				
    		  <configuration>					
    			   <verbose>true</verbose>					
    			   <overwrite>true</overwrite>				
    		  </configuration>				
    		  <dependencies>					
    			   <dependency>							
    					<groupId>com.fit-time.utils</groupId>	
    					<artifactId>mybatis-generator-pagination</artifactId>					
    					<version>0.0.1-SNAPSHOT</version>					
    			   </dependency>				
    		  </dependencies>			
    	 </plugin>		
    </plugins>
###2.编辑generatorConfig.xml
    <generatorConfiguration>
         ...
          <context id="mysql" targetRuntime="MyBatis3">
              ...
               <plugin type="com.fittime.utils.PaginationPlugin" />
               <plugin type="com.fittime.utils.InsertSelectKeyPlugin" />
         </context>
    </generatorConfiguration>
###3.mvn mybatis-generator:generate
###4.check result and enjoy
