package pre.task.traffic.info.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pre.task.traffic.info.dto.StationInfoDto;

@SpringBootTest
@MybatisTest
class InfoServiceTest {
	@Autowired
    private InfoService infoService;

    @Test
    void insertTrafficData() throws Exception {    	
        int result = 0;
		try {
			result = infoService.insertTrafficData("/data/서울교통공사_승하차인원_20191231.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
        assertThat(result).isEqualTo(288);
    }
    
    @Test
    void selectDailyAvgStationUser() {
    	List<StationInfoDto> stationInfoDtoList = infoService.selectDailyAvgStationUser();
        assertThat(stationInfoDtoList.size()).isEqualTo(1);
        assertThat(stationInfoDtoList.get(0).getStationName()).isEqualTo("station");
    }
    
    @Test
    void selectMonthlyAvgStationUser() {
    	StationInfoDto stationInfoDto = infoService.selectMonthlyAvgStationUser();
        assertThat(stationInfoDto.getStationName()).isEqualTo("station");
    }
    
    @Test
    void selectMaxDiffStationUser() {
    	StationInfoDto stationInfoDto = infoService.selectMaxDiffStationUser();
        assertThat(stationInfoDto.getStationName()).isEqualTo("station");
    }
}