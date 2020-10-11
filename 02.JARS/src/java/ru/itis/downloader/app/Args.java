package ru.itis.downloader.app;

import com.beust.jcommander.Parameter;

public class Args{
	@Parameter(names = {"--mode"})
	public String mode;

	@Parameter(names = {"--files"})
	public String files;
	
	@Parameter(names = {"--count"})
	public int count;
	
	@Parameter(names = {"--folder"})
	public String folder;

}