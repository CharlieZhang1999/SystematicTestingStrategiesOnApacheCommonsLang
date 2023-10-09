import re

def count_lines_of_code(file_path):
    with open(file_path, 'r') as file:
        lines = file.readlines()

    code_lines = 0

    in_comment_block = False

    for line in lines:
        # Remove leading and trailing whitespaces
        cleaned_line = line.strip()

        # Skip blank lines
        if not cleaned_line:
            continue

        # Check for the start of a comment block
        if cleaned_line.startswith('/*'):
            in_comment_block = True
            continue

        # Check for the end of a comment block
        if cleaned_line.endswith('*/'):
            in_comment_block = False
            continue

        # Skip lines inside comment blocks
        if in_comment_block:
            continue

        # Check for line comments and remove them
        cleaned_line = re.sub(r'//.*', '', cleaned_line)

        # If the line is not empty after removing comments, it's a code line
        if cleaned_line:
            code_lines += 1

    return code_lines

if __name__ == "__main__":
    java_file_path = "main/java/org/apache/commons/lang3/time/StopWatch.java"  # Replace with your Java file path
    lines_of_code = count_lines_of_code(java_file_path)
    print(f"Lines of code (excluding comments and blank lines): {lines_of_code}")
