package in.co.rays.project4.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import in.co.rays.project4.bean.StudentBean;
import in.co.rays.project4.exception.ApplicationException;
import in.co.rays.project4.exception.DuplicateRecordException;
import in.co.rays.project4.model.StudentModel;


/**
 * For Student model testing
 * @author Lalit Kewat
 *
 */
public class StudentTest {
	/**
	 * Model object to test
	 */

	public static StudentModel model = new StudentModel();

	/**
	 * Main method to call test methods.
	 *
	 * @param args
	 * @throws ParseException
	 */

	public static void main(String[] args) throws Exception {
	//	testAdd();
		testUpdate();
		//testFindByPK();
	//	testDelete();
	//	testFindByEmailId();
	//	testSearch();
	//	testList();
	}

	/**
	 * Tests add a Student
	 *
	 * @throws ParseException
	 */
	public static void testAdd() throws ParseException {
		try {
			StudentBean bean = new StudentBean();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date fo = new Date();
			
			Timestamp ts = new Timestamp(fo.getTime());
		//	bean.setId(2);
			bean.setFirstName("ram");
			bean.setLastName("kumawat");
			bean.setDob(sdf.parse("31/12/1990"));
			bean.setCollegeName("xyz");
			bean.setMobileNo("9165254357");
			bean.setEmail("bipon.chandore@nenosystems.com");
			bean.setCollegeID(4);
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDateTime(ts);
			bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
			long pk = model.add(bean);
			StudentBean addedbean = model.findByPK(pk);
			if (addedbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}
	
	 /**
     * Tests update a Student
	 * @throws Exception 
	 * @throws ApplicationException 
     */
    public static void testUpdate() throws ApplicationException, Exception {

        
           StudentBean bean=new StudentBean(); 
           bean.setCollegeID(1);
            bean.setCollegeName("Medicaps");
            bean.setFirstName("ankit");
            bean.setLastName("sharma");
          //  bean.setDob(dob);
            bean.setMobileNo("234567890");
            bean.setEmail("bairagi123@gmail.com");
            bean.setCreatedBy("Admin");
            bean.setModifiedBy("Admin");
            bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
            bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
            
            bean.setId(2);
            model.update(bean);

           /* StudentBean updatedbean = model.findByPK(1);
            if (!"rr".equals(updatedbean.getFirstName())) {
                System.out.println("Test Update fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }*/
    }
    
    /**
     * Tests find a Student by PK.
     */
    public static void testFindByPK() {
        try {
            StudentBean bean = new StudentBean();
            long pk = 1;
            bean = model.findByPK(pk);
            if (bean == null) {
                System.out.println("Test Find By PK fail");
            }
            System.out.println(bean.getId());
            System.out.println(bean.getCollegeID());
            System.out.println(bean.getCollegeName());
            System.out.println(bean.getFirstName());
            System.out.println(bean.getLastName());
            System.out.println(bean.getDob());
            System.out.println(bean.getMobileNo());
            System.out.println(bean.getEmail());
            
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * Tests delete a Student
     */
    public static void testDelete() {

        try {
            StudentBean bean = new StudentBean();
            long pk = 1;
            bean.setId(pk);
            model.delete(bean);
            StudentBean deletedbean = model.findByPK(pk);
            System.out.println("Success and delete");
            if (deletedbean != null) {
                System.out.println("Test Delete fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Tests find a Student by Emailid.
     */
    public static void testFindByEmailId() {
        try {
            StudentBean bean = new StudentBean();
            bean = model.findByEmailId("shubham123@gmail.com");
            if (bean != null) {
                System.out.println("Test Find By EmailId fail");
            }
            System.out.println(bean.getId());
            System.out.println(bean.getFirstName());
            System.out.println(bean.getLastName());
            System.out.println(bean.getDob());
            System.out.println(bean.getMobileNo());
            System.out.println(bean.getEmail());
            System.out.println(bean.getCollegeID());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Tests get Search
     */
    public static void testSearch() {

        try {
            StudentBean bean = new StudentBean();
            List list = new ArrayList();
            bean.setFirstName("Shubham");
            list = model.search(bean, 0, 0);
            if (list.size() < 0) {
                System.out.println("Test Serach fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (StudentBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getFirstName());
                System.out.println(bean.getLastName());
                System.out.println(bean.getDob());
                System.out.println(bean.getMobileNo());
                System.out.println(bean.getEmail());
                System.out.println(bean.getCollegeID());
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * Tests get List.
     */
    public static void testList() {

        try {
            StudentBean bean = new StudentBean();
            List list = new ArrayList();
            list = model.list(1, 10);
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (StudentBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getFirstName());
                System.out.println(bean.getLastName());
                System.out.println(bean.getDob());
                System.out.println(bean.getMobileNo());
                System.out.println(bean.getEmail());
                System.out.println(bean.getCollegeID());
                System.out.println(bean.getCreatedBy());
                System.out.println(bean.getCreatedDateTime());
                System.out.println(bean.getModifiedBy());
                System.out.println(bean.getModifiedDateTime());
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

	
}


	


