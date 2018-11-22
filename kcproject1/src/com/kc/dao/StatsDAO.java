package com.kc.dao;

import java.util.List;

import com.kc.vo.BranchInfo;
import com.kc.vo.Stats;

public interface StatsDAO {
	
	
	
	
	/**
	 * @param branch_code_list		branch_code 값만 들어있는 ArrayList (쿼리에서 지점_테이블의 지점값을 넣기 위함)
	 * @param tocharDate				'YY'   'YY/MM'   'YY/MM/DD'   'YY/MM/DD HH24' 값을 가질 수 있음
	 * @param period					BETWEEN을 제외한 LIKE ' ' 의 조건으로 사용
	 * 										ex. 18/%, 18/11/%, 18/11/15%   등 월별/일별/시간별 가능
	 * @return	해당 구간의 모든 지점의 통계 row
	 */
	public List<Stats>	selectAllBranchsStats(List<String>branch_code_list, String tocharDate , String period);		
	

	
	/**[본사용]
	 * 전체 지점의 통계 가져오는 부분
	 * @param tocharDate	tocharDate 검색 단위
	 * 가질수 있는 값 : -YY/MM/DD HH24	-YY/MM/DD  - YY/MM   -YY
	 *  (service 단에서 조회구간  넘겨줌)
	 * @param startDate 검색할 시작일
	 * @param endDate 검색할 마지막일
	 * @return 해당 구간의 일자별 통계 row
	 */
	public List<Stats>	selectStatsAllBranchsPeriod(List<String>branch_code_list, String tocharDate, String startDate, String endDate);		//oinfo.order_date BETWEEN '18/11/01' and '18/11/15'
	//조회한 시작일부터 더하기 쭉쭉.
	
	
	/**[지점용]
	 * 이번달 지난달.
	 * @param startDate 검색할 월의 시작일 & 해당 검색월의 LAST_DAY( startDate )
	 * oinfo.order_date BETWEEN '18/11/01' and LAST_DAY('18/11/01')
	 * @return 월의 일자별 통계 row
	 */
	public List<Stats>	selectBranchStatsByMonth(String startDate);		// 

	

	
	/**[지점용]
	 * 시간대별 & 구간:어제,오늘
	 * @param date 검색할 일자
	 * @return 각 시간대별 통계 row
	 */
	public List<Stats>	selectBranchStatsPerTime(String date);
	

}
