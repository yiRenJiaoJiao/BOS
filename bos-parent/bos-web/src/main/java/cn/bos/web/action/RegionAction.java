package cn.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
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

import cn.bos.domain.base.Region;
import cn.bos.domain.base.Staff;
import cn.bos.domain.base.Standard;
import cn.bos.utils.PinYin4jUtils;


@Controller
@Scope("prototype")
@Namespace("/bc")
@ParentPackage("bos")
public class RegionAction extends BaseAction<Region>{
	
	/**
	 * 获取所有的区域集合
	 * @return
	 */
	@Action(value="region_findRegionsList" ,results={@Result(name="json" ,type="fastJson" ,params={"includeProperties","id,name"})})
	public String findRegionsList(){
		String  q = (String) getParameter("q");
		List<Region> list = facadService.getRegionService().findRregionsList(q);
		pushToValueStack(list);
		return "json";
	}
	
	/**
	 * 获取快递员的结果集，,并且进行分页查询 ,results={@Result(name="findAll",type="fastJson",params={"root","map"})}
	 * @return
	 */
	//,results={@Result(name="json", type="json")}
	@Action(value="region_finAll")
	public String findAll(){
	Specification<Region> specification = new Specification<Region>(){
			@Override
			public Predicate toPredicate(Root<Region> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				ArrayList<Predicate> list = new ArrayList<Predicate>();
				
				if(StringUtils.isNotBlank(model.getProvince())){
					list.add(cb.like(root.get("province").as(String.class), model.getProvince()+"%"));
				}
				if(StringUtils.isNotBlank(model.getCity())){
					list.add(cb.like(root.get("city").as(String.class), model.getCity()+"%"));
				}
				if(StringUtils.isNotBlank(model.getDistrict())){
					list.add(cb.like(root.get("district").as(String.class),model.getDistrict()+"%"));
				}
				if(StringUtils.isNotBlank(model.getShortcode())){
					list.add(cb.like(root.get("shortcode").as(String.class), model.getShortcode()+"%"));
				}
				//集合转换成数组
				Predicate ps[] = new Predicate[list.size()];
				return cb.and(list.toArray(ps));
			}
			
		};
		Page<Region> page2 = facadService.getRegionService().pageQuery(getPageRequest(), specification);
		setPage1(page2);
		return "findAll";
	}

/**
 * 批量上传
 * @return
 */
	@Action(value="region_uploadRegion")
	public String uploadRegion(){
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
            
            ArrayList<Region> regions = new ArrayList<>();
          //排除第一行数据
            for (Row r : sheet) {
            	if(r.getRowNum()==0){
            		continue;
            	}
            	 Region region = new Region();
            	 region.setId(r.getCell(0).getStringCellValue());
            	String province = r.getCell(1).getStringCellValue();
            	region.setProvince(province);
            	String city = r.getCell(2).getStringCellValue();
            	region.setCity(city);
            	String district = r.getCell(3).getStringCellValue();
            	region.setDistrict(district);
            	region.setPostcode(r.getCell(4).getStringCellValue());
            	//使用pinyin4j自动生成简记码
            	province = province.substring(0, province.length()-1);
            	city = city.substring(0, city.length()-1);
            	district = district.substring(0, district.length()-1);
            	String[] headCode ;
            	if(province.equalsIgnoreCase(city)){
            		headCode = PinYin4jUtils.getHeadByString(city+district);
            	}{
            		headCode = PinYin4jUtils.getHeadByString(province+city+district);
            	}
            	region.setShortcode(PinYin4jUtils.stringArrayToString(headCode));
            	region.setCitycode(PinYin4jUtils.hanziToPinyin(city));
            	//将每一行region添加到集合中
            	regions.add(region);
			}
          facadService.getRegionService().importData(regions);
          pushToValueStack(true); 
        } catch (Exception e) {
          System.out.println(e);
            pushToValueStack(false);
        }
		return "json";
	}
	

	
	/**
	 * 根据id获取快递员的信息
	 * @return
	 */
	@Action(value="region_findRegion")
	public String findRegion(){
		String id = (String) getParameter("id");
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
	
	/**
	 * 根据id批量作废收派标准
	 * @return
	 */
	@Action(value="region_deleteRegion")
	public String deleteRegion(){
		try {
			String ids = (String) getParameter("ids");
			String[] arrId = ids.split(",");
		
				facadService.getRegionService().deleteRegion(arrId);
				pushToValueStack(true);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.addActionError(this.getText("delete.region.error"));
			pushToValueStack(false);
		}
			
		return "json";
	}
	
	/**
	 * 保存前台的添加快递员的信息
	 * @return
	 * @throws Exception
	 */
	@Action(value="region_saveProvince",results={@Result(name="saveProvince",location="/WEB-INF/pages/base/region.jsp")})
	public String saveProvince() throws Exception {
		String haspda = (String) getParameter("haspda");
		//使用pinyin4j自动生成简记码
		String province = model.getProvince();
		String city = model.getCity();
		String district = model.getDistrict();
    	province = province.substring(0, province.length()-1);
    	city = city.substring(0, city.length()-1);
    	district = district.substring(0, district.length()-1);
    	String[] headCode ;
    	if(province.equalsIgnoreCase(city)){
    		headCode = PinYin4jUtils.getHeadByString(city+district);
    	}{
    		headCode = PinYin4jUtils.getHeadByString(province+city+district);
    	}
    	model.setShortcode(PinYin4jUtils.stringArrayToString(headCode));
    	model.setCitycode(PinYin4jUtils.hanziToPinyin(city));
		facadService.getRegionService().save(model);
		return "saveProvince";
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
