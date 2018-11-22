

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.kc.vo.BranchInfo;
import com.kc.vo.IngredientInfo;
import com.kc.vo.RestockingInfo;
import com.kc.vo.RestockingLine;


public class RestockingDAOTest {

	public static void main(String[] args) {
		try {
			String resource = "config/mybatis-config.xml";
			InputStream inputStream;
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			try {
				//RestockingDAO : selectAll Test
				/*List<RestockingInfo> list = new ArrayList<>();
				list = session.selectList("RestockingMapper.selectAll");
				for(RestockingInfo info:list) {
					System.out.println(info);
					List<RestockingLine> lines = new ArrayList<>();
					lines = info.getRestocking_lines();
					for(RestockingLine line:lines) {
						System.out.println("line : " +line);
					}
				}
				//OK
*/				
				
				//RestockingDAO : selectByProdFlag Test
				/*List<RestockingInfo> list = new ArrayList<>();
				list = session.selectList("RestockingMapper.selectByProdFlag",2);
				for(RestockingInfo info:list) {
					System.out.println(info);
					List<RestockingLine> lines = new ArrayList<>();
					lines = info.getRestocking_lines();
					for(RestockingLine line:lines) {
						System.out.println("line : " +line);
					}
				}*/
				//OK
				//RestockingDAO : insertRst Test
				
				RestockingInfo info = new RestockingInfo();
				BranchInfo branch = new BranchInfo();
				branch.setBranch_code("S0001");
				info.setBranch(branch);
				info.setReturn_no(44);
				List<RestockingLine> restocking_lines = new ArrayList<>();
				RestockingLine line1 = new RestockingLine();
				IngredientInfo ingredient1 = new IngredientInfo();
				ingredient1.setIngred_no(200);
				line1.setIngredient(ingredient1);
				line1.setRestocking_quantity(2);
				line1.setRsl_prod_state_flag("1");
				
				restocking_lines.add(line1);
				RestockingLine line2 = new RestockingLine();
				IngredientInfo ingredient2 = new IngredientInfo();
				ingredient2.setIngred_no(104);
				line2.setIngredient(ingredient2);
				line2.setRestocking_quantity(4);
				line2.setRsl_prod_state_flag("3");
				restocking_lines.add(line2);
				info.setRestocking_lines(restocking_lines);
				
				session.insert("RestockingMapper.insertInfo",info);
				System.out.println("info insert 성공");
				session.commit();
				List<RestockingLine> lines = new ArrayList<>();
				lines = info.getRestocking_lines();
				for(RestockingLine line:lines) {
					session.insert("RestockingMapper.insertLine",line);
					System.out.println("line insert 성공");
				}
				session.commit();
				
				//OK
				
				
			} finally {
			  session.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}