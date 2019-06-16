@echo off
set /p commonName="Enter common name for all certificates(localhost): "

IF EXIST eureka.jks DEL /F eureka.jks
IF EXIST zuul.jks DEL /F zuul.jks
IF EXIST auth.jks DEL /F auth.jks
IF EXIST accommodation.jks DEL /F accommodation.jks
IF EXIST reservation.jks DEL /F reservation.jks
IF EXIST megatravel.jks DEL /F megatravel.jks
IF EXIST bezbednostui.jks DEL /F bezbednostui.jks
IF EXIST agent.jks DEL /F agent.jks
IF EXIST eureka.crt DEL /F eureka.crt
IF EXIST zuul.crt DEL /F zuul.crt
IF EXIST auth.crt DEL /F auth.crt
IF EXIST accommodation.crt DEL /F accommodation.crt
IF EXIST reservation.crt DEL /F reservation.crt
IF EXIST megatravel.crt DEL /F megatravel.crt
IF EXIST agent.crt DEL /F agent.crt
IF EXIST bezbednostui.crt DEL /F bezbednostui.crt
IF EXIST bezbednost.crt DEL /F bezbednost.crt
IF EXIST bezbednost.jks DEL /F bezbednost.jks

echo Generating keystores...

keytool -genkey -dname "CN=%commonName%, OU=I, O=I, L=T, ST=On, C=CA" -alias eureka -validity 3650 -keyalg RSA -keystore eureka.jks -keypass password -storepass password

keytool -genkey -dname "CN=%commonName%, OU=I, O=I, L=T, ST=On, C=CA" -alias zuul -validity 3650 -keyalg RSA -keystore zuul.jks -keypass password -storepass password

keytool -genkey -dname "CN=%commonName%, OU=I, O=I, L=T, ST=On, C=CA" -alias accommodation -validity 3650 -keyalg RSA -keystore accommodation.jks -keypass password -storepass password

keytool -genkey -dname "CN=%commonName%, OU=I, O=I, L=T, ST=On, C=CA" -alias auth -validity 3650 -keyalg RSA -keystore auth.jks -keypass password -storepass password

keytool -genkey -dname "CN=%commonName%, OU=I, O=I, L=T, ST=On, C=CA" -alias reservation -validity 3650 -keyalg RSA -keystore reservation.jks -keypass password -storepass password

keytool -genkey -dname "CN=%commonName%, OU=I, O=I, L=T, ST=On, C=CA" -alias agent -validity 3650 -keyalg RSA -keystore agent.jks -keypass password -storepass password

keytool -genkey -dname "CN=%commonName%, OU=I, O=I, L=T, ST=On, C=CA" -alias megatravel -validity 3650 -keyalg RSA -keystore megatravel.jks -keypass password -storepass password

keytool -genkey -dname "CN=%commonName%, OU=I, O=I, L=T, ST=On, C=CA" -alias bezbednostui -validity 3650 -keyalg RSA -keystore bezbednostui.jks -keypass password -storepass password

keytool -genkey -dname "CN=%commonName%, OU=I, O=I, L=T, ST=On, C=CA" -alias bezbednost -validity 3650 -keyalg RSA -keystore bezbednost.jks -keypass password -storepass password

echo Distributing and importing...

keytool -export -keystore eureka.jks -alias eureka -file eureka.crt -storepass password
keytool -importcert -file eureka.crt -alias eureka -keystore zuul.jks -storepass password -noprompt
keytool -importcert -file eureka.crt -alias eureka -keystore accommodation.jks -storepass password -noprompt
keytool -importcert -file eureka.crt -alias eureka -keystore auth.jks -storepass password -noprompt
keytool -importcert -file eureka.crt -alias eureka -keystore reservation.jks -storepass password -noprompt
keytool -importcert -file eureka.crt -alias eureka -keystore agent.jks -storepass password -noprompt
keytool -importcert -file eureka.crt -alias eureka -keystore bezbednost.jks -storepass password -noprompt

keytool -export -keystore zuul.jks -alias zuul -file zuul.crt -storepass password
keytool -importcert -file zuul.crt -alias zuul -keystore eureka.jks -storepass password -noprompt
keytool -importcert -file zuul.crt -alias zuul -keystore auth.jks -storepass password -noprompt
keytool -importcert -file zuul.crt -alias zuul -keystore accommodation.jks -storepass password -noprompt
keytool -importcert -file zuul.crt -alias zuul -keystore reservation.jks -storepass password -noprompt
keytool -importcert -file zuul.crt -alias zuul -keystore agent.jks -storepass password -noprompt
keytool -importcert -file zuul.crt -alias zuul -keystore bezbednost.jks -storepass password -noprompt

keytool -export -keystore accommodation.jks -alias accommodation -file accommodation.crt -storepass password
keytool -importcert -file accommodation.crt -alias accommodation -keystore eureka.jks -storepass password -noprompt
keytool -importcert -file accommodation.crt -alias accommodation -keystore auth.jks -storepass password -noprompt
keytool -importcert -file accommodation.crt -alias accommodation -keystore zuul.jks -storepass password -noprompt
keytool -importcert -file accommodation.crt -alias accommodation -keystore reservation.jks -storepass password -noprompt
keytool -importcert -file accommodation.crt -alias accommodation -keystore agent.jks -storepass password -noprompt
keytool -importcert -file accommodation.crt -alias accommodation -keystore bezbednost.jks -storepass password -noprompt

keytool -export -keystore auth.jks -alias auth -file auth.crt -storepass password
keytool -importcert -file auth.crt -alias auth -keystore eureka.jks -storepass password -noprompt
keytool -importcert -file auth.crt -alias auth -keystore accommodation.jks -storepass password -noprompt
keytool -importcert -file auth.crt -alias auth -keystore zuul.jks -storepass password -noprompt
keytool -importcert -file auth.crt -alias auth -keystore reservation.jks -storepass password -noprompt
keytool -importcert -file auth.crt -alias auth -keystore agent.jks -storepass password -noprompt
keytool -importcert -file auth.crt -alias auth -keystore bezbednost.jks -storepass password -noprompt

keytool -export -keystore reservation.jks -alias reservation -file reservation.crt -storepass password
keytool -importcert -file reservation.crt -alias reservation -keystore eureka.jks -storepass password -noprompt
keytool -importcert -file reservation.crt -alias reservation -keystore accommodation.jks -storepass password -noprompt
keytool -importcert -file reservation.crt -alias reservation -keystore zuul.jks -storepass password -noprompt
keytool -importcert -file reservation.crt -alias reservation -keystore auth.jks -storepass password -noprompt
keytool -importcert -file reservation.crt -alias reservation -keystore agent.jks -storepass password -noprompt
keytool -importcert -file reservation.crt -alias reservation -keystore bezbednost.jks -storepass password -noprompt

keytool -export -keystore agent.jks -alias agent -file agent.crt -storepass password
keytool -importcert -file agent.crt -alias agent -keystore eureka.jks -storepass password -noprompt
keytool -importcert -file agent.crt -alias agent -keystore accommodation.jks -storepass password -noprompt
keytool -importcert -file agent.crt -alias agent -keystore zuul.jks -storepass password -noprompt
keytool -importcert -file agent.crt -alias agent -keystore auth.jks -storepass password -noprompt
keytool -importcert -file agent.crt -alias agent -keystore reservation.jks -storepass password -noprompt
keytool -importcert -file agent.crt -alias agent -keystore bezbednost.jks -storepass password -noprompt

keytool -export -keystore megatravel.jks -alias megatravel -file megatravel.crt -storepass password
keytool -importcert -file megatravel.crt -alias megatravel -keystore eureka.jks -storepass password -noprompt
keytool -importcert -file megatravel.crt -alias megatravel -keystore accommodation.jks -storepass password -noprompt
keytool -importcert -file megatravel.crt -alias megatravel -keystore zuul.jks -storepass password -noprompt
keytool -importcert -file megatravel.crt -alias megatravel -keystore auth.jks -storepass password -noprompt
keytool -importcert -file megatravel.crt -alias megatravel -keystore reservation.jks -storepass password -noprompt
keytool -importcert -file megatravel.crt -alias megatravel -keystore agent.jks -storepass password -noprompt
keytool -importcert -file megatravel.crt -alias megatravel -keystore bezbednost.jks -storepass password -noprompt

keytool -export -keystore bezbednostui.jks -alias bezbednostui -file bezbednostui.crt -storepass password
keytool -importcert -file bezbednostui.crt -alias bezbednostui -keystore eureka.jks -storepass password -noprompt
keytool -importcert -file bezbednostui.crt -alias bezbednostui -keystore accommodation.jks -storepass password -noprompt
keytool -importcert -file bezbednostui.crt -alias bezbednostui -keystore zuul.jks -storepass password -noprompt
keytool -importcert -file bezbednostui.crt -alias bezbednostui -keystore auth.jks -storepass password -noprompt
keytool -importcert -file bezbednostui.crt -alias bezbednostui -keystore reservation.jks -storepass password -noprompt
keytool -importcert -file bezbednostui.crt -alias bezbednostui -keystore agent.jks -storepass password -noprompt
keytool -importcert -file bezbednostui.crt -alias bezbednostui -keystore bezbednost.jks -storepass password -noprompt

keytool -export -keystore bezbednost.jks -alias bezbednost -file bezbednost.crt -storepass password
keytool -importcert -file bezbednost.crt -alias bezbednost -keystore eureka.jks -storepass password -noprompt
keytool -importcert -file bezbednost.crt -alias bezbednost -keystore accommodation.jks -storepass password -noprompt
keytool -importcert -file bezbednost.crt -alias bezbednost -keystore zuul.jks -storepass password -noprompt
keytool -importcert -file bezbednost.crt -alias bezbednost -keystore auth.jks -storepass password -noprompt
keytool -importcert -file bezbednost.crt -alias bezbednost -keystore reservation.jks -storepass password -noprompt
keytool -importcert -file bezbednost.crt -alias bezbednost -keystore agent.jks -storepass password -noprompt

copy accommodation.jks accommodation-service\src\main\resources
copy reservation.jks reservation-service\src\main\resources
copy auth.jks auth-service\src\main\resources
copy eureka.jks eureka-service\src\main\resources
copy zuul.jks zuul-server\src\main\resources
copy agent.jks agent-service\src\main\resources
copy bezbednost.jks ..\BSEP-master\bezbednost\src\main\resources

IF EXIST eureka.jks DEL /F eureka.jks
IF EXIST zuul.jks DEL /F zuul.jks
IF EXIST auth.jks DEL /F auth.jks
IF EXIST accommodation.jks DEL /F accommodation.jks
IF EXIST reservation.jks DEL /F reservation.jks
IF EXIST megatravel.jks DEL /F megatravel.jks
IF EXIST bezbednostui.jks DEL /F bezbednostui.jks
IF EXIST agent.jks DEL /F agent.jks
IF EXIST eureka.crt DEL /F eureka.crt
IF EXIST zuul.crt DEL /F zuul.crt
IF EXIST auth.crt DEL /F auth.crt
IF EXIST accommodation.crt DEL /F accommodation.crt
IF EXIST reservation.crt DEL /F reservation.crt
IF EXIST megatravel.crt DEL /F megatravel.crt
IF EXIST agent.crt DEL /F agent.crt
IF EXIST bezbednostui.crt DEL /F bezbednostui.crt
IF EXIST bezbednost.crt DEL /F bezbednost.crt
IF EXIST bezbednost.jks DEL /F bezbednost.jks



