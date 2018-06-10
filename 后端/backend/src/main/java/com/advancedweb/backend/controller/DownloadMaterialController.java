package com.advancedweb.backend.controller;

import com.advancedweb.backend.controller.json_model.CoursewareName;
import com.advancedweb.backend.controller.json_model.MaterialName;
import com.advancedweb.backend.repository.MaterialRepository;
import com.advancedweb.backend.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class DownloadMaterialController {

    @Autowired
    private NodeRepository nr;
    @Autowired
    private MaterialRepository mr;

    @RequestMapping(value = "/download_material/{course_id}/{mindmap_id}/{node_id}", method = RequestMethod.POST)
    public String download_material(@PathVariable String course_id, @PathVariable String mindmap_id,
                                    @PathVariable String node_id, @RequestBody MaterialName material,
                                    HttpServletRequest request, HttpServletResponse response) {

        final String filePath = "/Users/wy/Desktop/MindMapFileStorage/" + course_id + "/" + mindmap_id + "/" + node_id + "/material/";


        String material_name = material.getMaterial_name();
        String fileUrl = filePath + material_name;

        File file = new File(fileUrl);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition",
                    "attachment;fileName=" + material_name);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("success");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}
