# Clayfin
<h2> Employee Management System </h2>

<h3> Requirements </h3>
 Java<br>
 SpringBoot<br>
 MySQL<br>
 PostMan<br>

 <h3>Instrucations</h3>
 1) Clone this repository on your local system <br>
 2) Configure database details inside the project in application.properties <br>
 3) Configure path location for 100k employeees data file and ouput for 10 splitted csv data files like below<br>
 
 *inside service pakage in EmployeeCsvGenerator, CsvFileSplitter, CsvDataTransferService classes<br> 
 (don't create employees.cvs file it will genrated just prvoid folder path <br>
 <br>


 
![Genrator](https://github.com/SohamDoshi/Clayfin/assets/106314995/5287170a-06ef-40d5-8c23-fda2dfe7387d)

 
 4) run the project as a springBoot project using any IDE like STS <br>
 <br>

 
 ![springApp](https://github.com/SohamDoshi/Clayfin/assets/106314995/9cf902f8-c9b3-45b9-8892-bea6af380037)<br>
 <br>
 
 5) You need to change some data from 10 CSV file in order to get invaild data for ex - change some pancard numbers from the csv file.<br>

 6) Open PostMan to acess the URL, <br>
       a> Use GET method and URL = http://localhost:8080/create            to Create 100k employees data. <br>
       b> Use GET method and URL = http://localhost:8080/split             to Split the 100k employees data into 10 files. <br>
       c> Use GET method and URL = http://localhost:8080/transfer          to tranfer CSV data files into employee table in database. <br>
       d> Use GET method and URL = http://localhost:8080/vaild_transfer    to tranfer Valid data from employee table to valid_employees table. <br>

 7) Now You can check count on Mysql like this.<br>
 <br>

 
![mysql](https://github.com/SohamDoshi/Clayfin/assets/106314995/8236569b-8c28-4bd5-9a0b-331b54ab73da) <br>
