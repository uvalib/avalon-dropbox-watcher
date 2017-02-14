if [[ $EUID -ne 0 ]]; then
   echo "This script must be run as root" 
   exit 1
fi

case "$1" in
    -d|--daemon)
        $0 < /dev/null &> dropbox.log & disown 
        echo $! > run.pid
        exit 0
        ;;
    *)
        ;;
esac


JAVA_HOME=/usr/lib/jvm/jre-1.8.0-openjdk.x86_64
export JAVA_HOME

mvn clean compile exec:java -Dexec.mainClass=edu.virginia.lib.avalon.dropbox.DropboxDaemon -Dexec.args="/lib_content60/avalon/dropbox /lib_content67/AVALON_archive"
