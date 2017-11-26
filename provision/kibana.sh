# kibana 5.4.1
wget https://artifacts.elastic.co/downloads/kibana/kibana-5.4.1-amd64.deb
sha1sum kibana-5.4.1-amd64.deb
sudo dpkg -i kibana-5.4.1-amd64.deb
sudo update-rc.d kibana defaults 95 10
sudo -i service kibana start