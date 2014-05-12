smartsense
==========
Sensor Service Platform Mobile Applicatoin
Advisors: Jia Zhang, Bob Iannucci, Martin Griss, Steven Rosenberg, Anthony Rowe
Current contributors: Anubhav Aeron, Emilia Tourino, Ira Jain, Tushar Dadlani.

EXECUTIVE SUMMARY
==========
CMU SensorAndrew is the largest nation wide campus sensor network, our Sensor Data and Service Platform(SDSP). Supported by a cloud computing enrironment with high-performance database, SDSP provides a platform to enable and facilitate a variety of research projects at CMUSV in the areas of mobile services, internet of things, cloud computing, big data analytics, software as a service, and social services.
There are multiple devices from which the Sensor Platform is pulling sensor data. It gets readings from sensors across the campus, which are static. We use FireImp and FireFly to provide us readings. In addition, the contributors(mentioned above) have developed a Android mobile application which takes readings from all the sensors from mobile devices. Sensors range from Accelerometer, Magnetometer, Gyroscope, Light, Temperature etc. All these readings are collected using our application and are stored in HANA db. The interaction is done using RESTFul APIs.
