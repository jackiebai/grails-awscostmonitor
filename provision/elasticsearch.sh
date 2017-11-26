# elasticsearch 5.4.1
#wget -qO - https://artifacts.elastic.co/GPG-KEY-elasticsearch | sudo apt-key add -
#echo "deb https://artifacts.elastic.co/packages/5.x/apt stable main" | sudo tee -a /etc/apt/sources.list.d/elastic-5.x.list
#sudo apt-get update
#sudo apt-get install -y elasticsearch
wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-5.4.1.deb
sha1sum elasticsearch-5.4.1.deb 
sudo dpkg -i elasticsearch-5.4.1.deb
sudo update-rc.d elasticsearch defaults 95 10
sudo -i service elasticsearch start