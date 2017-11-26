# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure("2") do |config|
  # The most common configuration options are documented and commented below.
  # For a complete reference, please see the online documentation at
  # https://docs.vagrantup.com.

  # Every Vagrant development environment requires a box. You can search for
  # boxes at https://vagrantcloud.com/search.
  config.vm.box = "ubuntu/trusty64"

  # Disable automatic box update checking. If you disable this, then
  # boxes will only be checked for updates when the user runs
  # `vagrant box outdated`. This is not recommended.
   config.vm.box_check_update = false

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine. In the example below,
  # accessing "localhost:8080" will access port 80 on the guest machine.
  # NOTE: This will enable public access to the opened port
  #config.vm.network "forwarded_port", guest: 80, host: 8080

    config.vm.network "forwarded_port", guest: 8080, host: 8080 #grails
    config.vm.network "forwarded_port", guest: 27017, host: 27017 #mongodb
    config.vm.network "forwarded_port", guest: 5601, host: 5601 #kibana
    config.vm.network "forwarded_port", guest: 9200, host: 9200 #elasticsearch
    config.vm.network "forwarded_port", guest: 9300, host: 9300 #elasticsearch
    #config.vm.network "forwarded_port", guest: 5601, host: 5601 #kibana

  # Create a private network, which allows host-only access to the machine
  # using a specific IP.
  #config.vm.network "private_network", ip: "192.168.33.10"

  # Create a public network, which generally matched to bridged network.
  # Bridged networks make the machine appear as another physical device on
  # your network.
  config.vm.network "public_network"

  # Share an additional folder to the guest VM. The first argument is
  # the path on the host to the actual folder. The second argument is
  # the path on the guest to mount the folder. And the optional third
  # argument is a set of non-required options.
  # config.vm.synced_folder "../data", "/vagrant_data"
    config.vm.synced_folder "/Users/jackie.bai/awsCostMonitor", "/awsCostMonitor", create: "true"
  # Provider-specific configuration so you can fine-tune various
  # backing providers for Vagrant. These expose provider-specific options.
  # Example for VirtualBox:
  #
    config.vm.provider "virtualbox" do |vb|
      vb.customize ["modifyvm", :id, "--cpus", "2", "--memory", "8192"] #best practice for vagrant is to use 1/4 of the host's memory
      vb.gui = false  #if you want to see the screen, set this to true
  #
  #   # Customize the amount of memory on the VM:
  #   vb.memory = "1024"
  end
  #
  # View the documentation for the provider you are using for more
  # information on available options.

  # Enable provisioning with a shell script. Additional provisioners such as
  # Puppet, Chef, Ansible, Salt, and Docker are also available. Please see the
  # documentation for more information about their specific syntax and use.
    config.vm.provision "shell", path: "provision/java.sh"
    config.vm.provision "shell", path: "provision/grails.sh"
    config.vm.provision "shell", path: "provision/elasticsearch.sh"
    config.vm.provision "shell", path: "provision/kibana.sh"
    config.vm.provision "shell", path: "provision/mongodb.sh"

  #   apt-get update
  #   apt-get install -y apache2
  # SHELL
end
