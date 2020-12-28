#!/bin/bash 

# Objective: Start mockservicerunner
#
# 11282011 petervannes.nl Initial version
# 11302011 petervannes.nl Improved soapUI detection

# Do not change this line
export soapuipath=""
fqscript=$0

if [[ "${1}" == "respawn" ]] 
  then respawned="true"
  else respawned="false"
fi

# function  replaceconfig
# objective replace assigned path to soapuipath variable in
#           this script
#
function replaceconfig {
  path=`echo ${1} | sed 's/\//\\\\\//g'`
  sed -r "s/^(export\ssoapuipath)=.*$/\1=\"${path}\"/" $fqscript > \
  ${fqscript}.tmp \
  && cp ${fqscript}.tmp ${fqscript} \
  && rm -f ${fqscript}.tmp
  echo Respawning process ... 
  ./$0 respawn
  kill $$
}

# Check if current path to soapUI is a valid path
if [[ ((! -x $soapuipath) || (! -n $soapuipath)) && ($respawned != "true")]]
then

  echo Please be patient for first time initialization
  echo Detection of soapUI in progress ...

  # try to find soapUI in well known places
  sourcepath=( '/opt' ~ '/usr' '/' )

  for index in "${sourcepath[@]}"
  do
    echo Searching in ${index} ...
    soapuipath=`find ${index} -name "soapUI-?.?.?" -type f -perm /u=x,g=x 2>/dev/null`

    if [[ -x $soapuipath ]]
    then
      replaceconfig ${soapuipath}
    fi
  done
fi

# Stop if process is respawned without valid path to soapUI
if [[ ($respawned == "true") && ((! -x $soapuipath) || (! -n $soapuipath))]]
then
  echo Could not find installation of soapUI!
  exit
fi

# Get 'soapUI_HOME' 
soapuibasedir=`dirname ${soapuipath} 2>/dev/null`
if [ $? -ne  '0' ]
then 
  echo "Unable to determine soapUI installation directory" 
  exit 
fi

# Start mockservice
$soapuibasedir/mockservicerunner.sh -m"CustomerServiceSOAP MockService" \
CustomerServiceCRM-soapui-project.xml

