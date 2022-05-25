## How to set up Redis

MAC :  https://medium.com/@petehouston/install-and-config-redis-on-mac-os-x-via-homebrew-eb8df9a4f298
       https://redis.io/topics/quickstart

Window : https://redislabs.com/ebook/appendix-a/a-3-installing-on-windows/a-3-2-installing-redis-on-window/

Basic commond for Redis cache server:
$ brew install redis

Launch Redis on computer starts.
$ ln -sfv /usr/local/opt/redis/*.plist ~/Library/LaunchAgents

Start Redis server via “launchctl”.
$ launchctl load ~/Library/LaunchAgents/homebrew.mxcl.redis.plist

Start Redis server using configuration file.
$ redis-server /usr/local/etc/redis.conf

Stop Redis on autostart on computer start.
$ launchctl unload ~/Library/LaunchAgents/homebrew.mxcl.redis.plist

Location of Redis configuration file.
/usr/local/etc/redis.conf

Get Redis package information.
$ brew info redis

Test if Redis server is running.
$ redis-cli ping

Uninstall Redis and its files.
$ brew uninstall redis
$ rm ~/Library/LaunchAgents/homebrew.mxcl.redis.plist

https://www.baeldung.com/apache-pulsar