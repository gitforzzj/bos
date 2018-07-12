package com.itheima.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Decidedzone;
import com.itheima.bos.domain.Region;
import com.itheima.bos.domain.Subarea;
import com.itheima.bos.service.ISubareaService;
import com.itheima.bos.utils.FileUtils;
import com.itheima.bos.utils.PinYin4jUtils;
import com.itheima.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	@Resource
	private ISubareaService subareaService;
	
	private File regionFile;
	
	public File getRegionFile() {
		return regionFile;
	}

	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}

	public void setDecidedzoneId(String decidedzoneId) {
		this.decidedzoneId = decidedzoneId;
	}
	
	public String add() {
		subareaService.save(model);
		return LIST;
	}
	
	private String decidedzoneId;
	
	public String findSubareasGroupByProvince() {
		List<Object> list = subareaService.findSubareasGroupByProvince();
		this.java2json(list, new String[] {});
		return NONE;
		
	}
	
	public String findListByDecidedzoneId() {
		List<Subarea> list = subareaService.finListByDecidedzoneId(decidedzoneId);
		this.java2json(list, new String[] {"decidedzone","subareas"});
		return NONE;
	}
	
	
	
	
	public String pageQuery() {
		DetachedCriteria dc = pageBean.getDetachedCriteria();
		String addresskey = model.getAddresskey();
		if(StringUtils.isNotBlank(addresskey)) {
			dc.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
		}
		Region region = model.getRegion();
		if(region!=null) {
			String province = region.getProvince();
			String city = region.getCity();
			String district = region.getDistrict();
			dc.createAlias("region", "r");
			if(StringUtils.isNotBlank(province)) {
				dc.add(Restrictions.like("r.province","%"+province+"%"));
			}
			if(StringUtils.isNotBlank(city)) {
				dc.add(Restrictions.like("r.city","%"+city+"%"));
			}
			if(StringUtils.isNotBlank(district)) {
				dc.add(Restrictions.like("r.district","%"+district+"%"));
			}
		}
		subareaService.pageQuery(pageBean);
		this.java2json(pageBean, new String[] {"currentPage","pageSize","detachedCriteria","decidedzone","subareas"});
		return NONE;
	}
	
	public String importXls() throws FileNotFoundException, IOException {
		List<Subarea> subareaList = new ArrayList<Subarea>();
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
		HSSFSheet sheet = workbook.getSheetAt(0);
		for(Row row:sheet) {
			int rowNum = row.getRowNum();
			if(rowNum==0) {
				continue;
			}
			if(row.getCell(0)!=null) {
				String id = row.getCell(0).getStringCellValue();
				String regionId = row.getCell(1).getStringCellValue();/*
				String decidedzoneId = row.getCell(2).getStringCellValue();*/
				String addresskey = row.getCell(2).getStringCellValue();
				String startnum = row.getCell(3).getStringCellValue();
				String endnum = row.getCell(4).getStringCellValue();
				String single = row.getCell(5).getStringCellValue();
				String position = row.getCell(6).getStringCellValue();
				
				Region region = new Region();
				region.setId(regionId);
				Subarea subarea=new Subarea(id, region, null, addresskey, startnum, endnum, single, position);
				
				subareaList.add(subarea);
			}
			
			
		}
		String flag = "1";
        try{
        	subareaService.saveBatch(subareaList);//批处理保存到数据库中，如果抛异常返回标志字符串回前台，做出相应友好提示
        }catch(Exception e){
            flag = "0";
        }
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(flag);
		return LIST;
		
	}
	
	public String exportXls() throws IOException{
		List<Subarea> list = subareaService.findAll();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("分区数据");
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("开始编号");
		headRow.createCell(2).setCellValue("结束编号");
		headRow.createCell(3).setCellValue("位置信息");
		headRow.createCell(4).setCellValue("省市区");
		
		for(Subarea subarea : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getStartnum());
			dataRow.createCell(2).setCellValue(subarea.getEndnum());
			dataRow.createCell(3).setCellValue(subarea.getPosition());
			dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
		}
		String filename="分区数据.xls";
		String contentType = ServletActionContext.getServletContext().getMimeType(filename);
		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
		ServletActionContext.getResponse().setContentType(contentType);
		
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		filename = FileUtils.encodeDownloadFilename(filename,agent);
		ServletActionContext.getResponse().setHeader("content-disposition","attachment;filename="+filename);
		workbook.write(out);
		return NONE;
		
	}

	public String listajax() {
		List<Subarea> list = subareaService.findListNotAssociation();
		this.java2json(list, new String[] {"decidedzone","region"});
		return NONE;
	}
	
}
