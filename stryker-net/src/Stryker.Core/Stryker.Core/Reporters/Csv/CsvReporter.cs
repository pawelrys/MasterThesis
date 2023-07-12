using System.Collections.Generic;
using System.IO;
using System.IO.Abstractions;
using System.Linq;
using Spectre.Console;
using Stryker.Core.Mutants;
using Stryker.Core.Options;
using Stryker.Core.ProjectComponents;
using Stryker.Core.ProjectComponents.TestProjects;

namespace Stryker.Core.Reporters.Csv;

public class CsvReporter : IReporter
{
    private readonly StrykerOptions _options;
    private readonly IAnsiConsole _console;
    private readonly IFileSystem _fileSystem;

    public CsvReporter(StrykerOptions strykerOptions, IFileSystem fileSystem = null, IAnsiConsole console = null)
    {
        _options = strykerOptions;
        _console = console ?? AnsiConsole.Console;
        _fileSystem = fileSystem ?? new FileSystem();
    }

    public void OnMutantsCreated(IReadOnlyProjectComponent reportComponent, TestProjectsInfo testProjectsInfo)
    {
        // This reporter does not report during the testrun
    }

    public void OnStartMutantTestRun(IEnumerable<IReadOnlyMutant> mutantsToBeTested)
    {
        // This reporter does not report during the testrun
    }

    public void OnMutantTested(IReadOnlyMutant result)
    {
        // This reporter does not report during the testrun
    }

    public void OnAllMutantsTested(IReadOnlyProjectComponent reportComponent, TestProjectsInfo testProjectsInfo)
    {
        const string DescriptionTemplate = "ID,Mutation_Class,Mutant_Status,Line_Number,Mutation_Type,Test_Name";

        var tests = testProjectsInfo.TestFiles.ToList();
        var allTests = from testFile in tests select testFile.Tests.ToList();
        var finalTestsList = allTests.SelectMany(d => d).ToList();
        var filename = _options.ReportFileName + ".csv";
        var reportPath = Path.Combine(_options.ReportPath, filename);
        _fileSystem.Directory.CreateDirectory(Path.GetDirectoryName(reportPath)!);
        var streamWriter = new StreamWriter(reportPath);

        _console.WriteLine();
        streamWriter.WriteLine(DescriptionTemplate);
        foreach (var mutant in reportComponent.Mutants)
        {
            if (!mutant.AssessingTests.GetGuids().Any())
            {
                _console.WriteLine("ID: " + mutant.DisplayName);
                _console.WriteLine("Mutation Class: " + mutant.Mutation.OriginalNode.GetLocation());
                _console.WriteLine("Mutant Status: " + mutant.ResultStatus);
                _console.WriteLine("Line Number: " + mutant.Line);
                _console.WriteLine("Mutation Type: " + mutant.Mutation.DisplayName);
                var line =
                    $"{mutant.DisplayName},{mutant.Mutation.OriginalNode.GetLocation()},{mutant.ResultStatus},{mutant.Line},{mutant.Mutation.DisplayName}";
                streamWriter.WriteLine(line);
                _console.WriteLine();
            }

            foreach (var testId in mutant.AssessingTests.GetGuids())
            {
                var test = finalTestsList.Find(obj => obj.Id.Equals(testId));
                _console.WriteLine("ID: " + mutant.DisplayName);
                _console.WriteLine("Mutation Class: " + mutant.Mutation.OriginalNode.GetLocation());
                _console.WriteLine("Mutant Status: " + mutant.ResultStatus);
                _console.WriteLine("Line Number: " + mutant.Line);
                _console.WriteLine("Mutation Type: " + mutant.Mutation.DisplayName);
                _console.WriteLine("Test Name: " + test.Name);
                var line =
                    $"{mutant.DisplayName},{mutant.Mutation.OriginalNode.GetLocation()},{mutant.ResultStatus},{mutant.Line},{mutant.Mutation.DisplayName},{test.Name}";
                streamWriter.WriteLine(line);
                _console.WriteLine();
            }
        }

        streamWriter.Flush();
        _console.WriteLine();
        _console.WriteLine("All mutants have been tested, and your mutation score has been calculated");
    }
}
