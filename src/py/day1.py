import itertools


def read_numbers(filename):
    with open(filename) as f:
        lines = f.readlines()
        numbers = []
        for line in lines:
            numbers.append(int(line))
        return numbers


def find_pair_summing_to(numbers, target):
    for index1 in range(len(numbers)):
        for index2 in range(len(numbers)):
            if index1 != index2 and numbers[index1] + numbers[index2] == target:
                return numbers[index1], numbers[index2]


def subset_summing_to(numbers, subset_size, target):
    return next(filter(
        lambda subset: sum(subset) == target,
        itertools.combinations(numbers, subset_size)))


nums = read_numbers('../../inputs/day1_input.txt')
pair = find_pair_summing_to(nums, 2020)
same_pair = subset_summing_to(nums, 2, 2020)

print(f"pair = {pair}, product = {pair[0] * pair[1]}")
print(f"same_pair = {same_pair}, product = {same_pair[0] * same_pair[1]}")

triplet = subset_summing_to(nums, 3, 2020)
print(f"triplet = {triplet}, product = {triplet[0] * triplet[1] * triplet[2]}")