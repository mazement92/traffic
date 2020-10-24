package pre.task.traffic.info.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import pre.task.traffic.info.dto.StationInfoDto;

@SpringBootTest
@MybatisTest
class InfoServiceTest {
	@Autowired
    private InfoService infoService;

    @Test
    void insertTrafficData() throws Exception {    	
        int result = 0;
        ClassPathResource resource = new ClassPathResource("data/seoul.csv");
		try {
			Path path = Paths.get(resource.getURI());
			result = infoService.insertTrafficData(path.toString());
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