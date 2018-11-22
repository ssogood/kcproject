

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.kc.vo.Discard;
import com.kc.vo.IngredientInfo;


public class DiscardDAOTest {

	public static void main(String[] args) {
		try {
			String resource = "config/mybatis-config.xml";
			InputStream inputStream;
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			try {
				//DiscardDAO : selectAll Test
				/*List<Discard> list = new ArrayList<>();
				
				list = session.selectList("DiscardMapper.selectAll");
				for(Discard dc : list) {
					System.out.println(dc);
				}*/
				//OK
				
				
				//DiscardDAO : selectByProdFlag Test
				/*List<Discard> list = new ArrayList<>();
				list = session.selectList("DiscardMapper.selectByProdFlag","2");
				for(Discard dc : list) {
					System.out.println(dc);
				}*/
				//OK
				
				//DiscardDAO : insertDc Test
/*				Discard dc = new Discard();
				IngredientInfo ingredient = new IngredientInfo();
				ingredient.setIngred_no(200);
				dc.setIngredient(ingredient);
				dc.setDiscard_quantity(3);
				dc.setDc_prod_state_flag("2");
				session.insert("DiscardMapper.insertDc", dc);
				System.out.println("성공~");
				session.commit();
*/				
				//OK
				
			} finally {
			  session.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}