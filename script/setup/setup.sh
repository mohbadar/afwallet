# -----------------------------------------------------------------------------
# @author Mirwais Akrami
# @gmail m7akrami770@gmail.com
# Script to clone all ePayFrame repositories into a parent directory
# for an easier developer experience.
# -----------------------------------------------------------------------------
#!/bin/bash


declare -A REPOLIST
regex="(git\:).*(\.git)"
repoNameReg="[^\/]+$"

# Get the list of repositories in ePayFrame
REPOS=$(curl -X GET https://api.github.com/orgs/Anar-Framework/repos?per_page=100)

# go to the parent directory first
cd ../..

extract() {
    if [ $1 == '"git_url":' ]; then
        if [[ $2 =~ $regex ]]; then
            GIT_URL="${BASH_REMATCH[0]}"

            # Get repo name
            if [[ $GIT_URL =~ $repoNameReg ]]; then
                # remove the .git part
                if [[ "${BASH_REMATCH[0]}" =~ ^([^\.])+ ]]; then
                    REPOLIST["${BASH_REMATCH[0]}"]="$GIT_URL"
                fi
            else
                echo "Not found"
            fi
        fi
    fi
}

while IFS= read -r line
do
    # Pass one line of response at a time
    extract $line
done < <(printf '%s\n' "$REPOS")

# reset IFS to default back
IFS=' '

if [[ "${#REPOLIST[@]}" > 0 ]]; then
    # Clone the repos if not already cloned
    for val in "${!REPOLIST[@]}"; do
        if [[ $val != "anar-parent" ]]; then
            if [[ -d $val ]]; then
                echo -e "\e[32mRepo ${val} already exist\e[39m"
            else 
                echo -e "\e[33mRepository ${val} does not exist\e[39m"
                git clone ${REPOLIST[$val]}
            fi
        fi
    done
    else
        echo "No repositories available in Anar"
fi
