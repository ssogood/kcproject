

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.kc.vo.IngredientInfo;
import com.kc.vo.ReturnInfo;
import com.kc.vo.ReturnLine;


public class ReturnDAOTest {

	public static void main(String[] args) {
		try {
			String resource = "config/mybatis-config.xml";
			InputStream inputStream;
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			try {
				Map<String,String> map = new HashMap<>();
				//ReturnDAOOracle : selectAll Test
				//map.put("branch_code", "S0001");
				List<ReturnInfo> list = new ArrayList<>();
				//list = session.selectList("ReturnMapper.selectAll",map);
				/*for(ReturnInfo info:list) {
					System.out.println(info);
				}*/
				//OK
				//map.clear();
				//ReturnDAOOracle : selectRtnFlag Test
				//map.put("branch_code", "S0001");
				//map.put("return_flag", "1");
				//list = session.selectList("ReturnMapper.selectRtnFlag",map);
				
				/*for(ReturnInfo info:list) {
					System.out.println(info);
				}*/
				//OK
				//map.clear();
				//ReturnDAOOracle : updateRtnFlag Test
				/*map.put("branch_code", "S0001");
				map.put("return_state_flag", "3");
				map.put("return_no", "2");
				session.update("ReturnMapper.updateRtnFlag",map);
				System.out.println("성공");*/
				ReturnInfo info = new ReturnInfo();
				ReturnLine line1 = new ReturnLine();
				List<ReturnLine> returnlines = new ArrayList<>();
				IngredientInfo ingredient = new IngredientInfo();
				ingredient.setIngred_no(107);
				line1.setIngredient(ingredient);
				line1.setReturn_quantity(1);
				line1.setRtl_prod_state_flag("1");
				returnlines.add(line1);System.out.println(returnlines);
			
				
				ReturnLine line2 = new ReturnLine();
				IngredientInfo in1 = new IngredientInfo();
				in1.setIngred_no(102);
				line2.setIngredient(in1);
				line2.setReturn_quantity(1);
				line2.setRtl_prod_state_flag("3");
				System.out.println(line2);
				returnlines.add(line2);
				
				
				info.setReturn_lines(returnlines);
				map.put("branch_code", "s0001");
				//session.insert("ReturnMapper.insertInfo","s0001");
				session.insert("ReturnMapper.insertInfo",map);
				System.out.println("insertInfo Test");
				session.commit();
				List<ReturnLine> lines = info.getReturn_lines();
				
				map.put("branch_code", "s0001");
				for(ReturnLine line : lines) {
					//return_quantity,ingred_no,rtl_prod_state_flag
					
					String return_quantity = String.valueOf(line.getReturn_quantity());
					String ingred_no = String.valueOf(line.getIngredient().getIngred_no());
					String rtl_prod_state_flag = line.getRtl_prod_state_flag();
					map.put("return_quantity", return_quantity);
					map.put("ingred_no", ingred_no);
					map.put("rtl_prod_state_flag", rtl_prod_state_flag);
					System.out.println("들어가기 직전:"+line);
					
					session.insert("ReturnMapper.insertLine",map);
					System.out.println("들어간 후:"+line);
					System.out.println(":insertLine Test");
					session.commit();
				}
				
				session.commit();
			} finally {
			  session.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}