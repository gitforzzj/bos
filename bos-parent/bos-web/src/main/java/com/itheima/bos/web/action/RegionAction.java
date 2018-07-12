package com.itheima.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Region;
import com.itheima.bos.service.IRegionService;
import com.itheima.bos.utils.PageBean;
import com.itheima.bos.utils.PinYin4jUtils;
import com.itheima.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
@Controller
public class RegionAction extends BaseAction<Region> {
	
	private File regionFile;
	private String q;
	private String ids;

	@Autowired
	private IRegionService regionService;
	
	
	public String pageQuery() throws IOException {
		regionService.pageQuery(pageBean);
		java2json(pageBean,new String[] {"currentPage","pageSize","detachedCriteria","subareas"});
		return NONE;
		
	}
	
	public String add() {
		/*
		 * 用pingfor4自动生成shortcode和citycode
		 */
		String province = model.getProvince();
		String city = model.getCity();
		String district = model.getDistrict();
		
		province = province.substring(0, province.length() - 1);
		city = city.substring(0, city.length() - 1);
		district = district.substring(0, district.length() - 1);
		String info = province+city+district;
		String[] headByString = PinYin4jUtils.getHeadByString(info);
		String shortcode = StringUtils.join(headByString);
		String citycode = PinYin4jUtils.hanziToPinyin(city,"");
		model.setCitycode(citycode);
		model.setShortcode(shortcode);
		
		regionService.save(model);
		return LIST;
	}
	
	public String deleteBatch() throws Exception{
		regionService.deleteBatch(ids);
		return LIST;
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String edit() {
		/*
		 * 用pingfor4自动生成shortcode和citycode
		 */
		String province = model.getProvince();
		String city = model.getCity();
		String district = model.getDistrict();
		
		province = province.substring(0, province.length() - 1);
		city = city.substring(0, city.length() - 1);
		district = district.substring(0, district.length() - 1);
		String info = province+city+district;
		String[] headByString = PinYin4jUtils.getHeadByString(info);
		String shortcode = StringUtils.join(headByString);
		String citycode = PinYin4jUtils.hanziToPinyin(city,"");
		model.setCitycode(citycode);
		model.setShortcode(shortcode);
		
		regionService.update(model);
		return LIST;
	}
	
	public String importXls() throws FileNotFoundException, IOException {
		List<Region> regionList = new ArrayList<Region>();
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
		HSSFSheet sheet = workbook.getSheetAt(0);
		for(Row row:sheet) {
			int rowNum = row.getRowNum();
			if(rowNum==0) {
				continue;
			}
			String id = row.getCell(0).getStringCellValue();
			String province = row.getCell(1).getStringCellValue();
			String city = row.getCell(2).getStringCellValue();
			String district = row.getCell(3).getStringCellValue();
			String postcode = row.getCell(4).getStringCellValue();
			Region region = new Region(id, province, city, district, postcode, null, null, null);
			
			province = province.substring(0, province.length() - 1);
			city = city.substring(0, city.length() - 1);
			district = district.substring(0, district.length() - 1);
			String info = province+city+district;
			String[] headByString = PinYin4jUtils.getHeadByString(info);
			String shortcode = StringUtils.join(headByString);
			String citycode = PinYin4jUtils.hanziToPinyin(city,"");
			region.setCitycode(citycode);
			region.setShortcode(shortcode);
			regionList.add(region);
		}
		String flag = "1";
        try{
        regionService.saveBatch(regionList);//批处理保存到数据库中，如果抛异常返回标志字符串回前台，做出相应友好提示
        }catch(Exception e){
            flag = "0";
        }
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(flag);
		return LIST;
		
	}
	
	
	public String listajax() {
		List<Region> list =null;
		
		if(StringUtils.isNotBlank(q)) {
			list=regionService.findListByQ(q);
		}else{
			list=regionService.findAll();
		};
		this.java2json(list, new String[] {"subareas"});
		return NONE;
	}

	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}
	public void setQ(String q) {
		this.q = q;
	}
	
}
