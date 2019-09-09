package com.escst.face;

import com.escst.faceRecognition.service.FaceRecognitionService;
import com.escst.faceRecognition.vo.FaceRecognitionVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * @author jincheng
 * @desc 人脸识别测试
 * @date 2018/3/7 15:31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml",
        "classpath:/spring/spring-mvc.xml" })
public class FaceRecognitionServiceTest {

    @Autowired
    private FaceRecognitionService faceRecognitionService;

    @Test
    public void listCameraTest() {
        String constructionId = "rt2bfa3a5186f0d30151d3008cb40d99";
        List<FaceRecognitionVO> list = faceRecognitionService.listCamera(constructionId);
        System.out.println(list);

    }

    @Test
    public void listFaceTest() {
        FaceRecognitionVO faceRecognitionVO = new FaceRecognitionVO();
        faceRecognitionVO.setConstructionId("rt2bfa3a5186f0d30151d3008cb40d99");
        faceRecognitionVO.setEquipmentId("b9b5e712959948c9bcbc7b2272de9c21");
        List<FaceRecognitionVO> list = faceRecognitionService.listFace(faceRecognitionVO);
    }

    @Test
    public void listFaceCount() {
        FaceRecognitionVO faceRecognitionVO = new FaceRecognitionVO();
        faceRecognitionVO.setConstructionId("rt2bfa3a5186f0d30151d3008cb40d99");
        faceRecognitionVO.setGuid("8b0c6b0149db051436000000");
        List<FaceRecognitionVO> list = faceRecognitionService.listFaceCount(faceRecognitionVO);
    }


    @Test
    public void listPhotoTest() {
        FaceRecognitionVO faceRecognitionVO = new FaceRecognitionVO();
//        faceRecognitionVO.setCorrectStartDate("2018-03-02");
//        faceRecognitionVO.setCorrectEndDate("2018-03-05");
        faceRecognitionVO.setConstructionId("rt2bfa3a5186f0d30151d3008cb40d99");
        faceRecognitionVO.setCorrectStartDate("2018-03-07 00:00:13");
        faceRecognitionVO.setCorrectEndDate("2018-03-07 23:00:15");
//        faceRecognitionVO.setEquipmentId("b9b5e712959948c9bcbc7b2272de9c22");
//        List<FaceRecognitionVO> list = faceRecognitionService.listPhoto(faceRecognitionVO);
    }


    @Test
    public void listPhotoTrack() {
        FaceRecognitionVO faceRecognitionVO = new FaceRecognitionVO();
        faceRecognitionVO.setConstructionId("rt2bfa3a5186f0d30151d3008cb40d99");
        faceRecognitionVO.setGuid("8b0c6b0149db051436000000");
//        faceRecognitionVO.setCorrectStartDate("2018-03-07 12:00:13");
//        faceRecognitionVO.setCorrectEndDate("2018-03-07 12:00:15");
        List<FaceRecognitionVO> list = faceRecognitionService.listTrack(faceRecognitionVO);
    }
    
    @Test
    public void listFaceContrast() {
        FaceRecognitionVO faceRecognitionVO = new FaceRecognitionVO();
        faceRecognitionVO.setConstructionId("rt2bfa3a5186f0d30151d3008cb40d99");
//        List<FaceRecognitionVO> list = faceRecognitionService.listFaceContrast(faceRecognitionVO);

    }

}
