package cn.bos.web.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import cn.bos.domain.base.DecidedZone;
import cn.bos.domain.base.Region;
import cn.bos.domain.base.Staff;
import cn.bos.domain.base.Standard;
import cn.bos.domain.base.Subarea;
import cn.bos.service.impl.FacadService;
import cn.bos.utils.DownLoadUtils;
import cn.bos.utils.PinYin4jUtils;


@Controller
@Scope("prototype")
@Namespace("/bc")
@ParentPackage("bos")
public class SubareaAction extends BaseAction<Subarea>{
	
	//subareas_findSubareas
	@Action(value="subareas_findSubareas",results={@Result(name="findSubareas" ,type="fastJson",params={"includeProperties","sid,addresskey,position"})})
	public String findSubareas(){
		String  decidedZoneId = (String) getParameter("decidedZoneId");
		List<Subarea> list = facadService.getSubareaService().findAllByDecidedZoneId(decidedZoneId);
		pushToValueStack(list);
		return "findSubareas";
	}
	
	/**
	 * 根据id获取所有的定区信息
	 * @return
	 */
	//decidedZone_findAllByDecidedZoneId?decidedZoneId="+rowData.id  ,params={"includeProperties","sid,addresskey,position"})}
		@Action(value="subarea_findAllByDecidedZoneId" ,results={@Result(name="findAllByDecidedZoneId" ,type="fastJson")})
		public String findAllByDecidedZoneId(){
			String  decidedZoneId = (String) getParameter("decidedZoneId");
			List<Subarea> list = facadService.getSubareaService().findAllByDecidedZoneId(decidedZoneId);
			pushToValueStack(list);
			return "findAllByDecidedZoneId";
		}
		
		//subarea_findAllSubareas
		/**
		 * 获取所有的区域集合
		 * @return
		 */
		@Action(value="subarea_findAllSubareasById" ,results={@Result(name="findAllSubareasById" ,type="fastJson",params={"includeProperties","sid,addresskey,position"})})
		public String findAllSubareasById(){
			String  id = (String) getParameter("id");
			List<Subarea> list = facadService.getSubareaService().findSubareaListsAssociationAndMySelf(id);
			pushToValueStack(list);
			return "findAllSubareasById";
		}
		
	/**
	 * 获取所有的区域集合
	 * @return
	 */
	@Action(value="subarea_findAllSubareaNoAssosiation" ,results={@Result(name="findAllSubareaNoAssosiation" ,type="fastJson",params={"includeProperties","sid,addresskey,position"})})
	public String findAllSubareaNoAssosiation(){
		List<Subarea> list = facadService.getSubareaService().findSubareaListNOAssosiation();
		pushToValueStack(list);
		return "findAllSubareaNoAssosiation";
	}
	
	/**
	 * 获取快递员的结果集，,并且进行分页查询
	 * @return
	 */
	//,results={@Result(name="json", type="json")}
	@Action(value="subarea_finAll")
	public String findAll(){
			
		Specification<Subarea> specification = getSpecification();
		Page<Subarea> page2 = facadService.getSubareaService().pageQuery(getPageRequest(), specification);
		setPage1(page2);
		return "findAll";
	}
	
	
	private Specification<Subarea> getSpecification() {
		Specification<Subarea> specification = new Specification<Subarea>(){
				@Override
				public Predicate toPredicate(Root<Subarea> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					
					ArrayList<Predicate> list = new ArrayList<Predicate>();
					//连接region
					if(model.getRegion()!=null){
						Join<Subarea, Region> regionJoin = root.join(root.getModel().getSingularAttribute("region",Region.class), JoinType.LEFT);
			              if(StringUtils.isNotBlank(model.getRegion().getProvince())){
			            	  Predicate p1 = cb.like(regionJoin.get("province").as(String.class), "%"+model.getRegion().getProvince()+"%");
			            	  list.add(p1);
			              }
			              if(StringUtils.isNotBlank(model.getRegion().getCity())){
			            	  Predicate p2 = cb.like(regionJoin.get("city").as(String.class), "%"+model.getRegion().getCity()+"%");
			            	  list.add(p2);
			              }
			              if(StringUtils.isNotBlank(model.getRegion().getDistrict())){
			            	  Predicate p3 = cb.like(regionJoin.get("district").as(String.class), "%"+model.getRegion().getDistrict()+"%");
			            	  list.add(p3);
			              }
			              
					}
					
					//连接decidedZone
					if(model.getDecidedZone()!=null){
						Join<Subarea, DecidedZone> decidedZoneJoin = root.join(root.getModel().getSingularAttribute("decidedZone",DecidedZone.class), JoinType.LEFT);
						if(StringUtils.isNotBlank(model.getDecidedZone().getId())){
							//Predicate p4 = cb.equal(decidedZoneJoin.get("id").as(String.class), model.getId());
							Predicate p4 = cb.equal(root.get("decidedZone").as(DecidedZone.class), model.getDecidedZone());
							  list.add(p4);
						}
					}
					
				//自身关键字查询
				if(StringUtils.isNotBlank(model.getAddresskey())){
					Predicate p5 = cb.like(root.get("addresskey").as(String.class), model.getAddresskey()+"%");
					  list.add(p5);
				}
					
					//集合转换成数组
				//Predicate[] predicates = list.toArray(new Predicate[0]);
					Predicate ps[] = new Predicate[list.size()];
					return cb.and(list.toArray(ps));
				}
				
			};
		return specification;
	}
	/**
	 * 查询所有,results={@Result(name="findAll",type="fastJson",params={"root","map"})} 
	 * @return
	 */
/*	@Action(value="subarea_finAll")
	public String findAll(){
		Page<Subarea> page2 =facadService.getSubareaService().findAll(getPageRequest());
		setPage(page2);
		return "findAll";
	}*/
	/**
	 * 保存编辑的信息
	 * @return
	 */
	//statt_checkTelephone
	@Action(value="subarea_saveEdit")
	public String saveEdit(){
		try {
			String regionId = (String) getParameter("region[id]");
			Region region = facadService.getRegionService().findOne(regionId);
			model.setRegion(region);
			facadService.getSubareaService().save(model);
			pushToValueStack(true);
		} catch (Exception e) {
			e.printStackTrace();
			pushToValueStack(false);
		}
		return "json";
	}
	
/**
 * 批量上传
 * @return
 */
	@Action(value="subarea_uploadSubArea")
	public String uploadSubArea(){
		try {
		
            // 创建对Excel工作簿文件的引用
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(upload));
            // 创建对工作表的引用。
            // 本例是按名引用（让我们假定那张表有着缺省名"Sheet1"）
            HSSFSheet sheet = workbook.getSheetAt(0);
            // 也可用getSheetAt(int index)按索引引用，
            // 在Excel文档中，第一张工作表的缺省索引是0，
            // 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);
            // 读取左上端单元
            //获取表单数据封装到region中
            
            ArrayList<Subarea> aubareas = new ArrayList<>();
          //排除第一行数据
            for (Row r : sheet) {
            	if(r.getRowNum()==0){
            		continue;
            	}
            	 Subarea subarea = new Subarea();
            	 subarea.setId(r.getCell(0).getStringCellValue());
            	 //通过id获取region
            	 String  regionId  = r.getCell(1).getStringCellValue();
            	 Region region = facadService.getRegionService().findOne(regionId);
            	 subarea.setRegion(region);
            	subarea.setAddresskey(r.getCell(2).getStringCellValue());
            	subarea.setStartnum(r.getCell(3).getStringCellValue());
            	subarea.setEndnum(r.getCell(4).getStringCellValue());
            	String singleCode = r.getCell(5).getStringCellValue();
            	subarea.setSingle(singleCode.charAt(0));
            	String position = r.getCell(6).getStringCellValue();
            	
            	//定区未作，报出空指针异常
            	
            	subarea.setPosition(position);
            	aubareas.add(subarea);
			}
          facadService.getSubareaService().importData(aubareas);
          pushToValueStack(true); 
        } catch (Exception e) {
        	e.printStackTrace();
          System.out.println(e);
            pushToValueStack(false);
        }
		return "json";
	}
	
	/**
	 * 导出 subarea_export
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("resource")
	@Action(value="subarea_export")
	public String export() throws Exception{

	    try {
	    	
			Specification<Subarea> specification = getSpecification();
			List<Subarea> list = facadService.getSubareaService().findSubarea(specification);
	    	
			//创建HSSFWorkbook对象(excel的文档对象)  
			     HSSFWorkbook wb = new HSSFWorkbook();  
			//建立新的sheet对象（excel的表单）  
			HSSFSheet sheet=wb.createSheet("管理分区表");  
			
		//	List<Subarea> list = facadService.getSubareaService().findAll();
			HSSFRow row=sheet.createRow(0);  
			//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个  
			HSSFCell cell1=row.createCell(0);  
			      //设置单元格内容  
			cell1.setCellValue("分区编号");  
			HSSFCell cell2=row.createCell(1);  
			//设置单元格内容  
			cell2.setCellValue("区域编码");  
			HSSFCell cell3=row.createCell(2);  
			//设置单元格内容  
		   cell3.setCellValue("关键字");  
		   HSSFCell cell4=row.createCell(3);  
		  //设置单元格内容  
			cell4.setCellValue("起始号");  
			HSSFCell cell5=row.createCell(4);  
			//设置单元格内容  
			cell5.setCellValue("结束号");  
			HSSFCell cell6=row.createCell(5);  
			//设置单元格内容  
			cell6.setCellValue("单双号");  
			HSSFCell cell7=row.createCell(6);  
			//设置单元格内容  
			cell7.setCellValue("位置信息");  
			
			for (int i=0;i<list.size();i++) {
				Subarea subarea = list.get(i);
				 //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个  
				 HSSFRow row1=sheet.createRow(i+1);  
			    //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个  
			   row1.createCell(0).setCellValue(subarea.getId());
			   row1.createCell(1).setCellValue(subarea.getRegion().getId());  
			   row1.createCell(2).setCellValue(subarea.getAddresskey());  
			   row1.createCell(3).setCellValue(subarea.getStartnum());  
			   row1.createCell(4).setCellValue(subarea.getEndnum()); 
			   row1.createCell(5).setCellValue(subarea.getSingle()); 
			   row1.createCell(6).setCellValue(subarea.getPosition());  
			
			}
   
			//输出Excel文件  
			String  fileName="管理分区表.xlsx";
	
			ServletContext context = ServletActionContext.getServletContext();
			HttpServletResponse response = getResponse();
			response.setHeader("Content-Disposition",
					"attachment;filename="+DownLoadUtils.getAttachmentFileName(fileName, ServletActionContext.getRequest().getHeader("user-agent")));
			response.setContentType(context.getMimeType(fileName));
			ServletOutputStream ops = response.getOutputStream();
			wb.write(ops);
			ops.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new RuntimeException("导出失败");
		}  
	        return NONE;
	}
	
	
	/**
	 * 根据id获取快递员的信息
	 * @return
	 */
	/*@Action(value="region_findRegion",results={@Result(name="json" ,type="json")})
	public String findRegion(){
		String id = (String) getParameter("id");
		HashMap<String, Object> map = new HashMap<>();
		Region region=null;
		if(id!=null){
			region = facadService.getRegionService().findOne(id);
		//	map.put("region", region);
		}else{
			this.addActionError(this.getText("find.staff.error"));
			//map.put("staff", "0");
			region=null;
		}
		pushToValueStack(region);
		return "json";
	}
	*/
	/**
	 * 根据id批量作废收派标准
	 * @return
	 */
//	subarea_deleteSubarea
	@Action(value="subarea_deleteSubarea")
	public String deleteSubarea(){
		try {
			String ids = (String) getParameter("ids");
			String[] arrId = ids.split(",");
			facadService.getSubareaService().deleteSubarea(arrId);
			pushToValueStack(true);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.addActionError(this.getText("delete.subarea.error"));
			pushToValueStack(false);
		}
			
		return "json";
	}
	
	/**
	 * 保存前台的添加快递员的信息
	 * @return
	 * @throws Exception
	 */
	@Action(value="subarea_saveSubarea",results={@Result(name="saveSubarea",location="/WEB-INF/pages/base/subarea.jsp")})
	public String saveSubarea() throws Exception {
		facadService.getSubareaService().save(model);
		return "saveSubarea";
	}
	
	
	/**
	 * 校验添加的编码是否唯一
	 * @return
	 */
	/*@Action(value="staff_checkId",results={@Result(name="json",type="json")})
	public String checkId(){
		Staff staff = facadService.getStaffService().findOne(model.getId());
		if(staff==null){
			pushToValueStack(true);
		}else{
			pushToValueStack(false);
		}
		return "json";
	}*/
}
