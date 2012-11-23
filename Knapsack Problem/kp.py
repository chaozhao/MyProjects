#!/usr/bin/env python2.6

# Copyright (c) 2010 Peter Robert Sutton
# 
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
# 
# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.
# 
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
# THE SOFTWARE.

from __future__ import print_function

import math
import sys


class Item(object):
    _str_value_pad = 0
    _str_weight_pad = 0
    def __init__(self, value, weight):
        self._value = value
        self._weight = weight
        # This pprint stuff should really be in string_solution_matrix.
        if math.ceil(math.log10(value)) > Item._str_value_pad:
            Item._str_value_pad = int(math.ceil(math.log10(value)))
        if math.ceil(math.log10(weight)) > Item._str_value_pad:
            Item._str_weight_pad = int(math.ceil(math.log10(weight)))
    def __str__(self):
        return "(V:{0:{1}d}, W:{2:{3}d})".format(self.value,
                                                 self._str_value_pad,
                                                 self.weight,
                                                 self._str_weight_pad)
    @property
    def value(self):
        return self._value
    @property
    def weight(self):
        return self._weight


class KnapsackProblem(object):
    @staticmethod
    def _create_matrix(width, height, default_value):
        return [[default_value] * width for row_index in xrange(height)]    
    def __init__(self, capacity, items):
        self._capacity = capacity
        self._items = items
        self._sm = \
            self._create_matrix(self._capacity + 1, len(self.items) + 1, 0)
        self._km = \
            self._create_matrix(self._capacity + 1, len(self.items) + 1, None)
        for i in xrange(len(self._km[0])):
            self._km[0][i] = False
        self._step = self._stepper().next
        self._steps = 0
    def __str__(self):
        return "".join(["\n\nSOLUTION MATRIX (STEP: %d)\n\n" % self._steps,
                        self._str_matrix(self._sm),
                        "\n\nKEEP MATRIX (STEP: %d)\n\n" % self._steps,
                        self._str_matrix(self._km),])
    def _stepper(self):
        for item_index, item in enumerate(self.items, 1):
            for capacity in xrange(self.capacity + 1):
                if item.weight <= capacity and \
                   self._sm[item_index - 1][capacity - item.weight] \
                        + item.value > \
                   self._sm[item_index - 1][capacity]:
                    self._sm[item_index][capacity] = \
                        self._sm[item_index - 1][capacity - item.weight] \
                            + item.value
                    self._km[item_index][capacity] = True
                else:
                    self._sm[item_index][capacity] = \
                        self._sm[item_index - 1][capacity]
                    self._km[item_index][capacity] = False
                self._steps += 1
                yield True
        raise StopIteration
    @property
    def capacity(self):
        return self._capacity
    @property
    def items(self):
        return self._items
    def _str_matrix(self, matrix):
        item_strings = ["NONE"]
        item_strings.extend(list(map(str, self.items)))
        item_max_len = max(len(i) for i in item_strings)


        header_title = "Capacity"
        header_title_pad = 1
        if len(header_title) + header_title_pad > item_max_len:
            item_max_len = len(header_title) + header_title_pad

        string = ["{0:>{1}s}|".format(header_title, item_max_len)]

        header_pad = 1
        headers = map(str, xrange(self.capacity + 1))
        header_width = max(len(i) for i in headers) + header_pad
        
        string.append("".join(
            "{0:>{1}s}".format(header, header_width) for header in headers))

        string = ["".join(string), "\n"]
        string.append("-" * (len(string[0])))
        string.append("\n")

        for item, item_string in enumerate(item_strings):
            string.append("{0:>{1}s}|".format(item_string, item_max_len))
            for i, sol in enumerate(matrix[item]):
                if self._km[item][i] is None:
                    string.append("{0:>{1}s}".format("-", header_width))
                else:
                    string.append("{0:{1}d}".format(sol, header_width))
            string.append("\n")
        del string[-1]

        return "".join(string)
    def get_solution(self):
        capacity = self.capacity
        sol = []
        for i in xrange(len(self.items), 0, -1):
            item = self.items[i - 1]
            if self._km[i][capacity]:
                sol.append(item)
                capacity -= item.weight
        return (self._sm[-1][-1],sol)
    def step(self):
        try:
            self._step()
        except StopIteration:
            return False
        else:
            return True


def main(args):
    if len(args) <= 1:
        args = [None, 18, "12,4", "10,6", "8,5", "11,7", "14,3", "7,1", "9,6"]
    items = list(Item(*map(int, item.split(","))) for item in args[2:])

    kp = KnapsackProblem(int(args[1]), items)
    ff = False
    silent = False
    while True:
        if not silent:
            print(kp)
        if not ff:
            try:
                text = raw_input("Step: Return, FF: f, Exit: ^C ? ")
            except KeyboardInterrupt:
                break
            if text == "f":
                ff = True
            elif text == "s":
                silent, ff = True, True
        if not kp.step():
            break
    value, items = kp.get_solution()
    print("\n\nSOLUTION\n    Value: %d\n    Items:" % value)
    print("\n".join("        %s" % i for i in items))

if __name__ == "__main__":
    main(sys.argv)
