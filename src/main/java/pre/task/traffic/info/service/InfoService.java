package pre.task.traffic.info.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

import pre.task.traffic.info.dao.InfoDao;
import pre.task.traffic.info.dto.StationInfoDto;

@Service
public class InfoService {
	@Autowired
	private InfoDao infoDao;
	
	/**
	 * CSV 데이터 전송
	 * @param dataPath String
	 * @return int
	 * @throws Exception
	 */
	public int insertTrafficData(String dataPath) throws Exception {
		int resultCnt = 0;
		try {
			// 파일 형식 검증. csv 파일이 아닐 경우 예외 발생
			String[] fileType = dataPath.split("\\.");
			if(!fileType[fileType.length-1].equals("csv")) {
				throw new Exception("파일 형식 오류. csv 파일을 입력해주세요");
			}
			
			CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(dataPath),"euc-kr"));
			String[] line = reader.readNext();
			
			while((line = reader.readNext()) != null) {
				StationInfoDto stationInfoDto = new StationInfoDto();
				stationInfoDto.setStationId(line[1]);
				stationInfoDto.setStationName(line[2]);
				
				// 역 번호와 역 명을 먼저 STATION 테이블에 INSERT
				resultCnt += infoDao.insertTrafficStationData(stationInfoDto);
				
				for(int i=3; i<line.length; i++) {
					stationInfoDto.setMonth(i-2); //데이터는 1월부터 입력되어 있습니다.
					String users = line[i].replaceAll(",", ""); // 천단위에 새겨진 , 제거 
					stationInfoDto.setMonthlyStationUser(Integer.parseInt(users));
					
					// 월별 이용자 수 MONTHLY_STATION에 INSERT
					infoDao.insertTrafficMonthlyUserData(stationInfoDto);
				}
			}
			return resultCnt;
		} catch(IOException ioe) {
			throw new IOException("파일 입력 과정에서 문제가 발생했습니다.");
		} catch(NumberFormatException ne) {
			throw new NumberFormatException("잘못된 데이터입니다. https://www.data.go.kr/data/15044249/fileData.do 데이터를 이용해주세요.");
		} catch(DuplicateKeyException de) {
        	throw new DuplicateKeyException("중복된 역번호가 존재합니다.");
        } catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * 일평균 인원 상위 10개 역 명 + 인원 수 조회
	 * @return List<StationInfoDto>
	 */
	public List<StationInfoDto> selectDailyAvgStationUser() {
		return infoDao.selectDailyAvgStationUser();
	}
	
	/**
	 * 월 인원 수 평균 최하위 역 명 + 인원 수 조회
	 * @return StationInfoDto
	 */
	public StationInfoDto selectMonthlyAvgStationUser() {
		return infoDao.selectMonthlyAvgStationUser();
	}
	
	/**
	 * 최대 최소 월 인원수 차 최상위 역 명 조회
	 * @return StationInfoDto
	 */
	public StationInfoDto selectMaxDiffStationUser() {
		return infoDao.selectMaxDiffStationUser();
	}
}