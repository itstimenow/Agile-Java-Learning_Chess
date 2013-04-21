#! /bin/bash

# ================================
# Set up the shell environment for Agile Java Learning - SIS
# ================================


run_ant() {
    echo "Running ant from my script ............"
    
    buildfile_specified=`_is_buildfile_option_specified`
    
    # if buildfile already specified, run ant with the original arguments
    # directly
    if [ $buildfile_specified ]; then
        ant $*
        return 0
    fi
    
    # otherwise, find buildfile first, and then run ant with the fined 
    # buildfile
    build_file=$(_find_build_file)
    
    if [ $build_file ]; then
        ant -buildfile $build_file $*
    else
        echo "**** build.xml not found! ****"
    fi
}

_is_buildfile_option_specified() {
    for arg in $*
    do
        if [ $arg == "-buildfile" ]; then
            return 0
        fi
    done
    
    return 1
}

_find_build_file() {
    directory=`pwd`
    build_file=""
    
    while $(_is_same_directory $directory $base_dir) \
          || $(_is_subdirectory $directory $base_dir)
    do
        if [ -f $directory/build.xml ]; then
            build_file=$directory/build.xml
            break
        else
            directory=`dirname $directory`
        fi
    done
    
    echo $build_file
}

# ----------------------------------
# Check if the first directory is a subdirectory of the second directory
# ----------------------------------
_is_subdirectory() {
    first_dir=$1
    second_dir=$2
    
    # remove the trailing slash end '/' if available
    first_dir="$(dirname $first_dir)/$(basename $first_dir)"
    second_dir="$(dirname $second_dir)/$(basename $second_dir)"
    
    first_dir_path_length=$(expr length $first_dir)
    second_dir_path_length=$(expr length $second_dir)
    
    if [ $first_dir_path_length -gt $second_dir_path_length ]; then
        # length + 1 in order to extract the '/'
        sub_dir_path_length=$(expr $second_dir_path_length + 1)
        
        sub_dir=$(expr substr $first_dir 1 $sub_dir_path_length)
        
        if [ $sub_dir == "$second_dir/" ]; then
            return 0
        fi
    fi
    
    return 1
}

# ----------------------------------
# Check if the first directory is same as the second directory
# ----------------------------------
_is_same_directory() {
    first_dir=$1
    second_dir=$2
    
    if [ $first_dir == $second_dir ]; then
        return 0
    elif [ $(dirname $first_dir) == $(dirname $second_dir) ] \
         && [ $(basename $first_dir) == $(basename $second_dir) ]; then
        return 0
    else
        return 1
    fi
}



##############################################################################
##############################################################################

base_dir=`dirname $BASH_SOURCE`

# shorten the prompt length
export PS1="\W$ "

cd $base_dir

alias ge="gedit"
alias runant="run_ant"

