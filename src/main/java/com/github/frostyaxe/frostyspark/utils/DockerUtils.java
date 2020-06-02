package com.github.frostyaxe.frostyspark.utils;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DefaultDockerClientConfig.Builder;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.command.LogContainerResultCallback;




/**
 * <b>Description:</b> This class handles the operation related to the Docker.
 * 
 * @author Abhishek Prajapati
 *
 */
@SuppressWarnings("deprecation")
public class DockerUtils
{

	
	/**
	 * <b>Description:</b> This getter method returns the object of the Config
	 * Builder for docker. With the help of returned object, user creates the
	 * configuration required to establish the connection with Docker.
	 * 
	 * </br>
	 * </br>
	 * <b>Example:</b> 
	 * 
	 * <p>{@code getBuilder() }</p>
     * <p>{@code .withDockerHost("tcp://docker.somewhere.tld:2376") } </p>
     * <p>{@code .withDockerTlsVerify(true) }</p>
     * <p>{@code .withDockerCertPath("/home/user/.docker") }</p>
     * <p>{@code .withRegistryUsername(registryUser) }</p>
     * <p>{@code .withRegistryPassword(registryPass) }</p>
     * <p>{@code .withRegistryEmail(registryMail) }</p>
     * <p>{@code .withRegistryUrl(registryUrl) }</p>
     * <p>{@code .build(); }</p>
	 * 
	 * </br>
	 * </br>
	 * <b>Configuration Items:</b>
	 * <ul>
	 * <li>DOCKER_HOST The Docker Host URL, e.g. tcp://localhost:2376 or unix:///var/run/docker.sock</li>
	 * <li>DOCKER_TLS_VERIFY enable/disable TLS verification (switch between http and https protocol)</li>
     * <li>DOCKER_CERT_PATH Path to the certificates needed for TLS verification</li>
     * <li>DOCKER_CONFIG Path for additional docker configuration files (like .dockercfg)</li>
     * <li>api.version The API version, e.g. 1.23.</li>
     * <li>registry.url Your registry's address.</li>
     * <li>registry.username Your registry username (required to push containers).</li>
     * <li>registry.password Your registry password.</li>
     * <li>registry.email Your registry email.</li>
	 * </ul>
	 * 
	 * @return Instance of the {@code Builder} to generate configuration
	 *         required for the connection establishment with docker.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public Builder getBuilder()
	{
		return DefaultDockerClientConfig.createDefaultConfigBuilder();
	}
	
	
	
	
	/**
	 * <b>Description:</b> This method helps the user to create the docker client
	 * configuration with the help of properties specified in the properties file.
	 * 
	 * </br>
	 * <b>Properties File Example: </b>
	 * 
	 * <p>
	 * {@code Properties properties = new Properties(); }
	 * </p>
	 * <p>
	 * {@code properties.setProperty("registry.email", "prajapatiabhishek1996@gmail.com"); }
	 * </p>
	 * <p>
	 * {@code properties.setProperty("registry.password", "Iwontshare"); }
	 * </p>
	 * <p>
	 * {@code properties.setProperty("registry.username", "frostyaxe"); }
	 * </p>
	 * <p>
	 * {@code properties.setProperty("DOCKER_CERT_PATH", "/home/frostyaxe/.docker/certs"); }
	 * </p>
	 * <p>
	 * {@code properties.setProperty("DOCKER_CONFIG", "/home/frostyaxe/.docker/"); }
	 * </p>
	 * <p>
	 * {@code properties.setProperty("DOCKER_TLS_VERIFY", "1"); }
	 * </p>
	 * <p>
	 * {@code properties.setProperty("DOCKER_HOST", "tcp://docker.frostyaxe.com:2376"); }
	 * </p>
	 * 
	 * @param properties : Properties containing the docker client configuration.
	 * 
	 * @return Docker client configuration built with the help of properties
	 *         specified by the user.
	 * 
	 * @since 1.0
	 * 
	 * @author Abhishek Prajapati
	 */
	public DefaultDockerClientConfig getCustomConfig(Properties properties)
	{
		return DefaultDockerClientConfig.createDefaultConfigBuilder().withProperties(properties).build();
	}
	
	
	
	
	/**
	 * <b>Description:</b> This getter method returns the default docker
	 * configuration instance. If user wants to create the customized configuration
	 * then user can achieve that by calling the following getter method
	 * {@code #getBuilder()}
	 * 
	 * @return Default configuration for the establishment of connection with
	 *         Docker.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public DefaultDockerClientConfig getDefaultConfg()
	{
		return DefaultDockerClientConfig.createDefaultConfigBuilder().build();
	}
	
	
	/**
	 * <b>Description:</b> This method returns the docker client that allows the
	 * user to communicate with docker.
	 * 
	 * @param config : Client configuration object required to create the docker
	 *               client's instance.
	 * 
	 * @return Docker client's instance.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public DockerClient getDockerClient(DockerClientConfig config)
	{
		return DockerClientBuilder.getInstance(config).build();
	}
	
	
	
	/**
	 * <b>Description:</b> This method lists all the running docker containers.
	 * 
	 * @param client : Instance of the docker client.
	 * @return List of all the running containers.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public List<Container> getRunningContainers(DockerClient client)
	{
		return client.listContainersCmd().exec();
	}
	
	
	
	
	/**
	 * <b>Description:</b> This method builds an image with the help of a
	 * Dockerfile.
	 * 
	 * @param client         : Instance of the Docker client
	 * @param dockerFilePath : Dockerfile path containing the instructions to build
	 *                       a docker image.
	 * @param tagName        : Tag to be added in the generated built docker image.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public String buildImage(DockerClient client, String dockerFilePath, String tagName)
	{
		Set<String> tags = new HashSet<String>();
		tags.add(tagName);
		
		String imageId = client.buildImageCmd()
		      .withDockerfile(new File(dockerFilePath))
		      .withTags(tags)
		      .exec(new BuildImageResultCallback())
		      .awaitImageId();
		
		return imageId;
	}
	
	
	
	
	/**
	 * <b>Description:</b> This method helps the user to create a docker container
	 * using the specified docker image. It returns {@code CreateContainerCmd} that
	 * allows the user to add more arguments if needed. By default, It accepts the
	 * name of the container and id of a docker image.
	 * 
	 * @param client  : Docker client instance
	 * @param imageId : Id of a docker image using which docker containers will get
	 *                created.
	 * @param name    : Name of the created docker container.
	 * 
	 * @return {@code CreateContainerCmd} with default configuration. If you want to use
	 *         the default config then call exec() using this instance.
	 *         
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public CreateContainerCmd getCreateContainerCmd(DockerClient client, String imageId, String name)
	{
		return client.createContainerCmd(imageId)
		      .withName(name);
	}
	
	
	
	
	/**
	 * <b>Description:</b> This method displays the logs of the specified docker
	 * container in console output.
	 * 
	 * @param client      : Docker client instance
	 * @param containerId : Id of the docker container of which logs need to be
	 *                    displayed on the console output.
	 * @throws InterruptedException : Interrupted exception.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public void displayLog(DockerClient client, String containerId) 
	{
		LogContainerCmd logContainerCmd = client.logContainerCmd(containerId).withStdOut(true).withStdErr(true) .withFollowStream(true).withTimestamps(true)
        .withTailAll();
		 try {
		        logContainerCmd.exec(new LogContainerResultCallback() {
		            @Override
		            public void onNext(Frame item) {
		              System.out.println(item.toString());
		            }
		        }).awaitCompletion();
		    } catch (InterruptedException e) {
		    System.out.println(e.getMessage());
		    }

	}
	
	public static void main(String args[])
	{
		DockerUtils dockerUtils = new DockerUtils();
		DockerClient client = dockerUtils.getDockerClient( dockerUtils.getDefaultConfg() );
		//dockerUtils.buildImage(client, System.getProperty("user.dir") + "/Dockerfile", "ubuntu:1.0");
		dockerUtils. displayLog(client, dockerUtils.getCreateContainerCmd(client, "maven:v1.0", "test").exec().getId() );
	}

	
}
