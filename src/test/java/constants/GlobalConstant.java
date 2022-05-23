package constants;


public class GlobalConstant {

	public enum FileNames {
		TestDataRelativePath("src/test/resources/testdata/"),
		UserProperties("User"),
		TestdataProperties("TestData");

		private String value;
		private FileNames(String value) {
			this.value = value;
		}

		public String toString() {
			return value;
		}
	}

	public enum CityNames {
		Mumbai, Delhi, Bangalore, Hyderabad, Ahmedabad, Chennai, Kolkata, Surat, Pune, Jaipur, Lucknow, Kanpur,
		Nagpur, Indore, Thane, Bhopal, Visakhapatnam, Pimpri_Chinchwad, Patna, Vadodara, Ghaziabad, Ludhiana,
		Agra, Nashik, Faridabad, Meerut, Rajkot, Kalyan_Dombivali, Vasai_Virar, Varanasi, Srinagar, Aurangabad,
		Dhanbad, Amritsar, Navi_Mumbai, Allahabad, Ranchi, Haora, Coimbatore, Jabalpur, Gwalior, Vijayawada,
		Jodhpur, Madurai, Raipur, Kota, Guwahati, Chandigarh, Solapur, Hubli_Dharwad, Bareilly, Moradabad,
		Karnataka, Gurgaon, Aligarh, Jalandhar, Tiruchirappalli, Bhubaneswar, Salem, Mira_Bhayander, Thiruvananthapuram,
		Bhiwandi, Saharanpur, Gorakhpur, Guntur, Bikaner, Amravati, Noida, Jamshedpur, Bhilai_Nagar, Warangal, Cuttack,
		Firozabad, Kochi, Bhavnagar, Dehradun, Durgapur, Asansol, Nanded_Waghala, Kolapur, Ajmer, Gulbarga, Jamnagar,
		Ujjain, Loni, Siliguri, Jhansi, Ulhasnagar, Nellore, Jammu, Sangli_Miraj_Kupwad, Belgaum, Mangalore, Ambattur,
		Tirunelveli, Malegoan, Gaya, Jalgaon, Udaipur, Maheshtala
	}

}
