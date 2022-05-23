/*
package pages;

import com.isometrix.selenium.framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AllControlPage extends BasePage {

	public AllControlPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@id='section_3dee4353-de18-4323-bd0b-fbc2b2cb7ba6']/i")
	private WebElement allcontrolButton;
	@FindBy(id = "InterfaceContent")
	private WebElement insideframe;
	@FindBy(id = "ifrMain")
	private WebElement frame;

	public void selectAllControl() {
		waitForLoading();
		waitForElement("#ifrMain");
		getDriver().switchTo().frame(frame);
		waitForElement(insideframe);
		waitForElement(allcontrolButton);
		clickOn(allcontrolButton);
		waitForLoading();
	}

	@FindBy(css = "#btnActAddNew")
	private WebElement addbutton;

	public void addentry() {
		sleep(4000);
		clickOn(addbutton);
	}

	@FindBy(css = ".tabpanel_mover")
	WebElement allcontrol_list;

	@FindBy(css = ".tabpanel_tab_content>div:nth-child(3)>ul>li:nth-child(1)>div:nth-child(1)")
	WebElement controltab;
	@FindBy(css = ".tabpanel_tab_content>div:nth-child(3)>ul>li:nth-child(2)>div:nth-child(1)")
	WebElement userdefinedtab;
	@FindBy(css = ".tabpanel_tab_content>div:nth-child(3)>ul>li:nth-child(3)>div:nth-child(1)")
	WebElement mdstab;
	@FindBy(css = ".tabpanel_tab_content>div:nth-child(3)>ul>li:nth-child(4)>div:nth-child(1)")
	WebElement mhdstab;
	@FindBy(css = ".tabpanel_tab_content>div:nth-child(3)>ul>li:nth-child(5)>div:nth-child(1)")
	WebElement userstab;
	@FindBy(css = ".tabpanel_tab_content>div:nth-child(3)>ul>li:nth-child(6)>div:nth-child(1)")
	WebElement submoduletab;

	public void selectentry(String entryname) {
		waitForElement(allcontrol_list);
		List<WebElement> ls = (List<WebElement>) driver.findElement(By.cssSelector(".tabpanel_mover>li"));
		for (int i = 0; i <= ls.size(); i++) {
			WebElement ele = driver.findElement(By.cssSelector(".tabpanel_mover>li:nth-child(" + i + 1 + ")"));
			String name = ele.getText();
			if (entryname.toLowerCase().equalsIgnoreCase(name)) {
				clickOn(ele);
			}
		}
	}

	// ===================================AllControl --> Control
	// Tab=========================================//

	@FindBy(css = "#control_8C30458C-A032-451B-A91F-4B8D6AA2CA08>div>div>input")
	private WebElement textbox1;
	@FindBy(css = "#control_25389548-90C6-42F6-BF0A-26CB4F7BC442>div>div>input")
	private WebElement textbox_defaultvalue;
	@FindBy(css = "#control_DC21E77A-F84A-48F8-96DF-56550D165C3D>div>div>textarea")
	private WebElement textarea1;
	@FindBy(css = "#control_8C4E1908-02BA-4338-8766-5465982E7E6E>div>div>input")
	private WebElement numeric_1;
	@FindBy(css = "#control_97BC46A6-1CFF-4493-8308-08440793DFFF>div>div>input")
	private WebElement numeric_2;
	@FindBy(css = "#control_1EF5BB30-2ADF-4C6C-83D3-58C8FCEACC43>div>span>span>input")
	private WebElement calendar;
	@FindBy(css = "#control_CEBDED7F-74A1-4EB0-8E96-9BA8383792B1>div>input")
	private WebElement default_calender;
	@FindBy(css = "#control_86098D77-1286-4D51-A374-E02929DAC7E9>div>input")
	private WebElement time;
	@FindBy(css = "#control_CEBDED7F-74A1-4EB0-8E96-9BA8383792B1>div>input")
	private WebElement default_time;
	@FindBy(css = "div[class='c-chk'] > div")
	private WebElement checkbox1;
	@FindBy(css = "#control_00C626FD-7FB7-4848-9FDB-D1AA94B55453>div>input")
	private WebElement formula1;
	@FindBy(css = "#control_0B950FBB-9C7E-4DD7-B9B2-A0BD32DB1366>div>input")
	private WebElement datalabel1;
	@FindBy(css = "#control_37B1778D-8662-491E-B51E-ADFED6D4C7DB>div>input")
	private WebElement lookup1;
	@FindBy(css = "#control_EEA86DA1-39FD-4230-B99F-82069521B122")
	private WebElement button1;
	@FindBy(css = "#btnSaveClear_form_CC657631-C8B3-4893-B83F-4F2F9A37F22A>div")
	private WebElement saveandclear;
	@FindBy(css = "#btnSave_form_CC657631-C8B3-4893-B83F-4F2F9A37F22A>div")
	private WebElement save;

	@FindBy(css = "#control_7565A2CA-D65B-4200-BBD9-25919819CBF8")
	private WebElement level;
	@FindBy(css = "#divForms>div:nth-child(3)>div>input")
	private WebElement level_searchBox;

	@FindBy(css = "#divContainer>div>div:nth-child(2)>div:nth-child(3)>ul>ul>li")
	private WebElement levelList;

	public void selectlevel(String select_value) {
		clickOn(level);
		sleep(1000);
		waitForElement(level_searchBox);
		inputText(level_searchBox, select_value);
		waitForElement("css=#divContainer>div>div:nth-child(2)>div:nth-child(3)>ul>ul");
		waitForElementVisible(levelList);
		waitForElementVisible(levelList.findElement(By.cssSelector("i")));
		sleep(1000);
		Actions action = new Actions(driver);
		action.click(levelList.findElement(By.cssSelector("i"))).build().perform();
		sleep(1000);
		WebElement element = getDriver()
				.findElement(By.cssSelector("#divContainer>div>div:nth-child(2)>div:nth-child(3)>ul>ul>li>ul>li"));
		action.click(element.findElement(By.cssSelector("a"))).click().build().perform();
	}

	@FindBy(css = "#control_EE38D60E-FE63-4801-BEE4-FE7C3D61A8D9")
	WebElement view_dropdown;
	@FindBy(css = "#divForms>div:nth-child(4)>div>input")
	WebElement view_searchbox;
	@FindBy(css = "#divForms>div:nth-child(4)>ul>ul>li>a")
	WebElement view_value;

	public void selectview(String view) {
		clickOn(view_dropdown);
		waitForElement(view_searchbox);
		inputText(view_searchbox, view);
		waitForElement(view_value);
		clickOn(view_value);
	}

	public void addentryControls(String level, String view, String textbox, String textbox_default, String textarea,
			String numeric1, String numeric2, String date1, String date2, String time1, String time2, String checknox,
			String formula, String datalabel, String lookup) throws ParseException {
		waitForElement(controltab);
		clickOn(controltab);

		selectlevel(level);

		selectview(view);
		inputText(textbox1, textbox);
		inputText(textbox_defaultvalue, textbox_default);
		inputText(textarea1, textarea);
		inputText(numeric_1, numeric1);
		inputText(numeric_2, numeric2);
		DateFormat dateFormate = new SimpleDateFormat("yyyy-mm-dd");
		Date date11 = dateFormate.parse(date1);
		Date date12 = dateFormate.parse(date2);
		inputText(calendar, dateFormate.format(date11).toString());
		inputText(default_calender, dateFormate.format(date12).toString());
		inputText(time, time1);
		inputText(default_time, time2);
		if (checknox.equalsIgnoreCase("true")) {
			   clickOn(checkbox1);
			  }
		inputText(formula1, formula);
		*/
/*
		 * inputText(datalabel1, datalabel); inputText(lookup1, lookup);
		 *//*

		clickOn(button1);
	}

	// =========================================== MDS Tab
	// ==========================================================
	@FindBy(css = "#control_91B2F73A-3690-43FE-940D-552056B22E51")
	WebElement mds_ddl1_dropdown;
	@FindBy(css = "#divForms>div:nth-child(7)>div>input")
	WebElement mds_ddl1_search;
	@FindBy(css = "#control_92856A7D-0F66-41FA-9454-A91E2ED28C4F")
	WebElement mds_ddl2_dropdown;
	@FindBy(css = "#divForms>div:nth-child(8)>div>input")
	WebElement mds_ddl2_search;
	@FindBy(css = "#control_7A7F4CBC-FEF4-48A9-B850-FC89A5E4F5C3>div>div>div>input")
	WebElement mds_cbl1_search;
	@FindBy(css = "#control_3A79E59D-A99F-4D58-8A76-EF667EEAE8F6>div>div>input")
	WebElement mds_cbl2_search;
	@FindBy(css = "#control_4914C465-BED7-4FAD-95E2-35F0FE4C854B")
	WebElement mds_ddl3_dropdown;
	@FindBy(css = "#divForms>div:nth-child(9)>div>input")
	WebElement mds_ddl3_search;
	@FindBy(css = "")
	WebElement mds_cbl3_search;
	@FindBy(css = "#btnSaveClear_form_CC657631-C8B3-4893-B83F-4F2F9A37F22A>div")
	WebElement mdsSaveClear;
	@FindBy(css = "#btnSave_form_CC657631-C8B3-4893-B83F-4F2F9A37F22A>div")
	WebElement mdsSAVE;

	public void mdsddl1(String mdl_search) {

		clickOn(mds_ddl1_dropdown);
		waitForElement(mds_ddl1_search);
		inputText(mds_ddl1_search, mdl_search);
		waitForElement("css=#divForms>div:nth-child(7)>ul>ul>li:nth-child(1)>a");
		clickOn("css=#divForms>div:nth-child(7)>ul>ul>li:nth-child(1)>a");
	}

	public void mdsddl2(String mdls_search) {
		clickOn(mds_ddl2_dropdown);
		waitForElement(mds_ddl2_search);
		inputText(mds_ddl2_search, mdls_search);
		waitForElement("css=#divForms>div:nth-child(8)>ul>ul>li:nth-child(1)>a");
		clickOn("css=#divForms>div:nth-child(8)>ul>ul>li:nth-child(1)>a");
	}

	public void mdscbl1(String[] list) {
		ArrayList<WebElement> ls = (ArrayList<WebElement>) driver
				.findElements(By.cssSelector("#control_7A7F4CBC-FEF4-48A9-B850-FC89A5E4F5C3>div>div>ul>ul>li"));
		String name;
		for (int i = 0; i < list.length - 1; i++) {
			name = list[i];
			for (int j = 0; j < ls.size() - 1; j++) {
				// if (name.equalsIgnoreCase(ls.get(j))){
				clickOn("css=control_7A7F4CBC-FEF4-48A9-B850-FC89A5E4F5C3>div>div>ul>ul>li:nth-of-type(2)>a>i:nth-of-type(1)");
				break;
				// }
			}
		}
	}

	public void mdscbl2(String[] list) {
		ArrayList<WebElement> ls = (ArrayList<WebElement>) driver
				.findElements(By.cssSelector("#control_3A79E59D-A99F-4D58-8A76-EF667EEAE8F6>div>div>ul>ul>li"));
		String name;
		for (int i = 0; i < list.length - 1; i++) {
			name = list[i];
			for (int j = 0; j < ls.size() - 1; j++) {
				// if (name.equalsIgnoreCase(ls.get(j))){
				clickOn("css=#control_3A79E59D-A99F-4D58-8A76-EF667EEAE8F6>div>div>ul>ul>li:nth-of-type(2)>a>i:nth-of-type(1)");
				break;
				// }
			}
		}
	}

	public void mdscbl3(String[] list) {
		ArrayList<WebElement> ls = (ArrayList<WebElement>) driver
				.findElements(By.cssSelector("#control_3A79E59D-A99F-4D58-8A76-EF667EEAE8F6>div>div>ul>ul>li"));
		for (int i = 0; i < list.length; i++) {
			String name = list[i];
			for (int j = 0; j < ls.size(); j++) {
				// if (name.equalsIgnoreCase(ls.get(j))){
				clickOn("css=#control_7CCC7A38-0023-4BDD-B7FD-324918430983>div>div>ul>ul>li:nth-of-type(2)>a>i:nth-of-type(1)");
				break;
				// }
			}
		}
	}

	public void mdsddl3(String mdls_search) {
		clickOn(mds_ddl3_dropdown);
		waitForElement(mds_ddl3_search);
		inputText(mds_ddl3_search, mdls_search);
		waitForElement("css=#divForms>div:nth-child(9)>ul>ul>li:nth-child(1)>a");
		clickOn("css=#divForms>div:nth-child(9)>ul>ul>li:nth-child(1)>a");

	}

	public void addentryMDS(String mdsDDL1, String mdsDDL2, String[] mdsCBL1, String[] mdsCBL2, String mdsDDL3,
			String[] mdsCBL3) {
		waitForElement(mdstab);
		sleep(2000);
		clickOn(mdstab);
		mdsddl1(mdsDDL1);
		mdsddl2(mdsDDL2);
		mdsddl3(mdsDDL3);
		mdscbl1(mdsCBL1);
		mdscbl2(mdsCBL2);
		mdscbl3(mdsCBL3);
		// mdsSAVE.click();
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++ MHDS TAB
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@FindBy(css = "#control_207F63FB-4EF5-419D-A6F6-8DE6BBE968CE")
	WebElement mhds_ddl1_dropdown;
	@FindBy(css = "#divForms>div:nth-child(10)>div>input")
	WebElement mhds_ddl1_search;
	@FindBy(css = "#control_B6304480-1CC9-4375-B856-8C4D097ADF0F")
	WebElement mhds_ddl2_dropdown;
	@FindBy(css = "#divForms>div:nth-child(11)>div>input")
	WebElement mhds_ddl2_search;
	@FindBy(css = "#control_04FA9482-231E-4A09-A104-C376E29F5C38>div>div>div>input")
	WebElement mhds_cbl1_search;
	@FindBy(css = "#control_26482219-7E7E-491D-907C-DB5616D84981>div>div>div>input")
	WebElement mhds_cbl2_search;
	@FindBy(css = "#control_77110266-ED6F-455E-A267-323E328ECA83")
	WebElement mhds_ddl3_dropdown;
	@FindBy(css = "#divForms>div:nth-child(12)>div>input")
	WebElement mhds_ddl3_search;
	@FindBy(css = "#btnSaveClear_form_CC657631-C8B3-4893-B83F-4F2F9A37F22A>div")
	WebElement mhdsSaveClear;
	@FindBy(css = "#btnSave_form_CC657631-C8B3-4893-B83F-4F2F9A37F22A>div")
	WebElement mhdsSAVE;

	public void mhdsDDL1(String mhdsddl1) {
		clickOn(mhds_ddl1_dropdown);
		waitForElement(mhds_ddl1_search);
		inputText(mhds_ddl1_search, mhdsddl1);
		waitForElement("css=#divForms>div:nth-child(10)>ul>ul>li:nth-child(1)>a");
		clickOn("css=#divForms>div:nth-child(10)>ul>ul>li:nth-child(1)>a");
	}

	public void mhdsDDL2(String mhdsddl2) {
		clickOn(mhds_ddl2_dropdown);
		waitForElement(mhds_ddl2_search);
		inputText(mhds_ddl2_search, mhdsddl2);
		waitForElement("css=#divForms>div:nth-child(11)>ul>ul>li:nth-child(1)>a");
		clickOn("css=#divForms>div:nth-child(11)>ul>ul>li:nth-child(1)>a");

	}

	public void mhdsDDL3(String mhdsddl3) {
		clickOn(mhds_ddl3_dropdown);
		waitForElement(mhds_ddl3_search);
		inputText(mhds_ddl3_search, mhdsddl3);
		waitForElement("css=#divForms>div:nth-child(12)>ul>ul>li:nth-child(1)>a");
		clickOn("css=#divForms>div:nth-child(12)>ul>ul>li:nth-child(1)>a");
	}

	public void mhdsCBL1(String[] list) {
		ArrayList<WebElement> ls = (ArrayList<WebElement>) driver
				.findElements(By.cssSelector("#control_04FA9482-231E-4A09-A104-C376E29F5C38>div>div>ul>ul"));
		String name;
		for (int i = 0; i < list.length; i++) {
			name = list[i];
			for (int j = 0; j < ls.size(); j++) {
				// if (name.equalsIgnoreCase(ls.get(j))){
				clickOn("css=#control_04FA9482-231E-4A09-A104-C376E29F5C38>div>div>ul>ul>li:nth-child(2)>a>i:nth-child(1)");
				break;
				// }
			}
		}
	}

	public void mhdsCBL2(String[] list) {
		ArrayList<WebElement> ls = (ArrayList<WebElement>) driver
				.findElements(By.cssSelector("#control_26482219-7E7E-491D-907C-DB5616D84981>div>div>ul>ul"));
		String name;
		for (int i = 0; i < list.length; i++) {
			name = list[i];
			for (int j = 0; j < ls.size(); j++) {
				// if (name.equalsIgnoreCase(ls.get(j))){
				clickOn("css=#control_26482219-7E7E-491D-907C-DB5616D84981>div>div>ul>ul>li:nth-child(1)>a>i:nth-child(1)");
				break;
				// }
			}
		}
	}

	public void addentryMHDS(String mhdsddl1, String mhdsddl2, String mhdsddl3, String[] mhdscbl1, String[] mhdscbl2) {
		waitForElement(mhdstab);
		clickOn(mhdstab);
		mhdsDDL1(mhdsddl1);
		mhdsDDL2(mhdsddl2);
		mhdsDDL3(mhdsddl3);
		mhdsCBL1(mhdscbl1);
		mhdsCBL2(mhdscbl2);
		// clickOn(mdsSAVE);
	}

	// ++++++++++++++++++++++++++++++++++++++++++++ USERS TAB
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@FindBy(css = "#control_B3EB8C82-A7DF-489D-817E-D8DF885C10F3")
	WebElement users_dropdown;
	@FindBy(css = "#divForms>div:nth-child(13)>ul>ul>li:nth-child(1)>a")
	WebElement users_search;
	@FindBy(css = "#control_B6304480-1CC9-4375-B856-8C4D097ADF0F")
	WebElement usersList_dropdown;
	@FindBy(css = "#btnSave_form_CC657631-C8B3-4893-B83F-4F2F9A37F22A>div")
	WebElement usersList_save;
	@FindBy(css = "#divForms>div:nth-child(13)>div>input")
	WebElement userList_search;

	public void users(String users) {
		clickOn(users_dropdown);
		// waitForElement(mhds_ddl1_search);
		// inputText(mhds_ddl1_search, users);
		String locator = "//*[@id='divForms']/div[13]/ul/ul/li/a[contains(text(),'" + users + "')]";
		waitForElement(locator);
		clickOn(locator);
	}

	@FindBy(css = "#control_E6040F74-FD0C-4550-AA96-BC78286540B4>div>div > div >input")
	private WebElement userChkeckBoxList;

	@FindBy(css = "#control_E6040F74-FD0C-4550-AA96-BC78286540B4>div>div>ul>ul>li:nth-child(1)>a>i:nth-child(1)")
	private WebElement userChkeckBoxFirsChekBox;

	public void userChklist(String[] list) {
		for (int i = 0; i < list.length; i++) {
			waitForElementVisible(userChkeckBoxList);
			inputText(userChkeckBoxList, list[i]);
			sleep(3000);
			clickOn(userChkeckBoxFirsChekBox);
		}
	}

	public void addentryUser(String users, String[] list) {
		waitForElement(userstab);
		clickOn(userstab);
		users(users);
		userChklist(list);
	}

	public void clickOnSave(){
		clickOn(usersList_save);
		waitForSaving();
		waitForLoading();
		sleep(8000);
	}

	//// +++++++++++++++++++++++++++++++++++++++++ User Defined Tab
	//// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	@FindBy(css = "#control_396E3F72-1F5E-413D-B2C8-F6C7BA34ECF4")
	WebElement userdefined_ddl1_dropdown;
	@FindBy(css = "#divForms>div:nth-child(5)>ul>ul>li:nth-child(1)>a")
	WebElement userdefined_ddl1_search;
	@FindBy(css = "#control_C50E5A30-9D91-40E9-9423-9EA9C1BFA0A5")
	WebElement userdefined_ddl2_dropdown;

	@FindBy(css = "#divForms>div:nth-child(6)>ul>ul>li:nth-child(1)>a")
	WebElement userdefined_ddl2_search;

	public void userdef_ddl1(String ddl1) {
		clickOn(userdefined_ddl1_dropdown);
		sleep(2000);
		try {
			clickOn(userdefined_ddl1_search);
		} catch (StaleElementReferenceException ex) {
			clickOn(userdefined_ddl1_search);
		}
		// waitForElement("css=#divForms>div:nth-child(5)>ul>ul>li:nth-child(1)>a");
		// clickOn("css=#divForms>div:nth-child(5)>ul>ul>li:nth-child(1)>a");
	}

	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void userdef_ddl2(String ddl2) {
		clickOn(userdefined_ddl2_dropdown);
		sleep(2000);
		waitForElement("css=#divForms>div:nth-child(6)>ul>ul>li:nth-child(1)>a");
		driver.findElement(By.cssSelector("#divForms>div:nth-child(6)>ul>ul>li:nth-child(1)>a")).click();
		// waitForElement("css=#divForms>div:nth-child(6)>ul>ul>li:nth-child(1)>a");
		// clickOn("css=#divForms>div:nth-child(6)>ul>ul>li:nth-child(1)>a");
	}

	@FindBy(css = "div[id='control_396E3F72-1F5E-413D-B2C8-F6C7BA34ECF4'] li[class='select3-placeholder']")
	private WebElement userDefineDll1DropDown;
	// clickOn(userDefineDll1DropDown);

	@FindBy(css = "#control_C639E7FD-0A9D-421B-9E39-112679B95164>div>div>ul>ul>li")
	private List<WebElement> userDefineCBL1DropDownList;;

	public void userdef_cbl1(String[] list) {

		for (int i = 0; i < list.length; i++) {
			System.out.println("data List " + i);
			System.out.println("Size of Li " + userDefineCBL1DropDownList.size());
			String name = list[i];
			int j = 1;
			for (WebElement element : userDefineCBL1DropDownList) {
				String value = driver.findElement(By.cssSelector(
						"#control_C639E7FD-0A9D-421B-9E39-112679B95164>div>div>ul" + ">ul>li:nth-of-type(" + j + ")>a"))
						.getText();
				System.out.println(value);
				if (name.equalsIgnoreCase(value.trim())) {
					mouseClick(driver
							.findElement(By.cssSelector("#control_C639E7FD-0A9D-421B-9E39-112679B95164>div>div>ul>"
									+ "ul>li:nth-of-type(" + j + ")>a>i:nth-of-type(1)")));
					break;
				}
				j++;
			}
		}
	}

	@FindBy(css = "#control_1AE06B7A-D4E9-42C6-ACFC-A088A28A07A0>div>div>ul>ul>li")
	private List<WebElement> userDefineCBL2DropDownList;

	public void userdefcbl2(String[] list) {
		for (int i = 0; i < list.length; i++) {
			String name = list[i];
			int j = 1;
			System.out.println("data List 1 " + i);
			System.out.println("Size of Li1 " + userDefineCBL2DropDownList.size());
			for (WebElement element : userDefineCBL2DropDownList) {

				String value = driver.findElement(By.cssSelector(
						"#control_1AE06B7A-D4E9-42C6-ACFC-A088A28A07A0>div>div>ul>" + "ul>li:nth-of-type(" + j + ")>a"))
						.getText();
				System.out.println(value);
				if (name.equalsIgnoreCase(value.trim())) {
					mouseClick(
							driver.findElement(By.cssSelector("#control_1AE06B7A-D4E9-42C6-ACFC-A088A28A07A0>div>div>"
									+ "ul>ul>li:nth-of-type(" + j + ")>a>i:nth-of-type(1)")));
					break;
				}
				j++;
			}
		}
	}

	public void addentry_Userdefined(String[] cbl1, String[] cbl2, String ddl1, String ddl2) {
		waitForElement(userdefinedtab);
		clickOn(userdefinedtab);
		userdef_cbl1(cbl1);
		userdef_ddl1(ddl1);
		userdef_ddl2(ddl2);
		userdefcbl2(cbl2);
	}

	@FindBy(xpath="//*[@class='k-selectable']/tbody/tr")
	private List<WebElement> listRecord;

	public boolean checkRecordNumberInList(String recordID){
		int i = 1;
		for(WebElement element : listRecord){
			String numberValue = element.findElement(By.xpath("//*[@class='k-selectable']/tbody/tr["+ i +"]/td[4]/span")).getText();
			System.out.println(numberValue);
			i++;
			if(numberValue.trim().equals(recordID))
				return true;
		}
		return false;
	}

	@FindBy(xpath="//*[@id='form_CC657631-C8B3-4893-B83F-4F2F9A37F22A']/div[1]/i[2]")
	private WebElement closeButton;

	public void clickOnCloseIcon(){
		clickOn(closeButton);
		waitForLoading();
	}

	@FindBy(xpath="(//*[@id='divPager']/div[1])[1]")
	private WebElement recordNoticationObj;

	public String captureAddedRecordID(){
		sleep(3000);
		return recordNoticationObj.getText().split("#")[1];
	}
	public void waitForLoading(){
		String locator= "//*[text()='Loading...']";
		WaitForElementNotPresent(locator, 40);
	}

	public void waitForSaving(){
		String locator= "//*[text()='Saving...']";
		WaitForElementNotPresent(locator, 40);
	}

	@FindBy(xpath="//*[text()='Parent Notification Module']")
	private WebElement parentNotificationModule;
	public void verifyAllControlPage() {
		verifyElementPresent(parentNotificationModule);

	}

	@FindBy(css="#btnActAddNew>div")
	private WebElement addButton;
	public void ClickOnAddButton() {
		clickOn(addButton);

	}


	@FindBy(xpath="//*[text()='Controls' and @class='title']")
	private WebElement controlsTab;
	public void verifyControlsTab() {
		verifyElementPresent(controlsTab);

	}

}
*/
