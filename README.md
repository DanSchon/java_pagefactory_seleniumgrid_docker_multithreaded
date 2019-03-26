instructions to run parallel, cross-browser tests on docker containers (using zalenium):
1. install docker
2. run the following docker commands on terminal to pull the images and then start the grid:
	a. docker pull elgalu/selenium
	b. docker pull dosel/zalenium
	c. docker run --rm -ti --name zalenium -p 4444:4444 -v /var/run/docker.sock:/var/run/docker.sock -v /tmp/videos:/home/seluser/videos --privileged dosel/zalenium start
3. set your RemoteWebDriver instance to use url: http://localhost:4444/wd/hub
4. navigate to selenium-grid console: http://localhost:4444/grid/console
5. navigate to zalenium dashboard: http://localhost:4444/dashboard/
6. on a new terminal, navigate to the project directory and run tests: mvn test
7. multithreaded test execution on chrome and firefox is executed, with a docker container for each browser
8. video recording of each test executed can be viewed on zalenium dashboard
9. bring down the selenium-grid: docker stop zalenium 