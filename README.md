<div align="center">

# Antikythera

**Automated test-generation tooling for Spring applications.**

<img src="https://img.shields.io/badge/Open--source_fork_with_internship_contributions-4F86FF?style=flat-square&labelColor=0B1224" alt="Open-source fork with internship contributions" /> <img src="https://img.shields.io/badge/Public_repository-4F86FF?style=flat-square&labelColor=0B1224" alt="Public repository" />

[Portfolio](https://kavindudamsith.tech/) &nbsp;|&nbsp; [LinkedIn](https://www.linkedin.com/in/kavindu-damsith-86696722a/) &nbsp;|&nbsp; [Email](mailto:kavindudamsith65@gmail.com)

</div>

---

## Overview

Antikythera analyses Java and Spring application source, builds a representation of controller behaviour and dependencies, and produces focused test assets. This repository is my fork of the open-source Cloud Solutions International project.

## My contribution

During my internship at Cloud Solutions International, I tested the dependency solver, investigated edge cases, and contributed reliability improvements. The wider tool is a team and open-source project.

## What it does

| Area | Details |
| --- | --- |
| **Source analysis** | Parses REST controllers, repositories, imports, annotations, and method calls with JavaParser. |
| **Dependency solving** | Builds graphs and resolves interfaces, DTOs, repositories, and referenced application types. |
| **Evaluation** | Models variables, arguments, reflection, and Spring behaviour at runtime. |
| **Test generation** | Creates controller requests, responses, truth tables, and generated Spring tests. |

## Repository map

| Path | Purpose |
| --- | --- |
| `src/main/java/.../parser/` | Controller and repository parsing. |
| `src/main/java/.../depsolver/` | Dependency graph and source-copy resolution. |
| `src/main/java/.../evaluator/` | Runtime evaluation and argument generation. |
| `src/main/java/.../generator/` | Spring test generation. |
| `src/test/` | Unit and integration coverage. |

## Technology

- **Java**
- **Spring**
- **JavaParser**
- **Maven**
- **JUnit**

## Local setup

```bash
mvn test
mvn package
```

## Status

Open-source fork with internship contributions.

## Links

- [Upstream project](https://github.com/Cloud-Solutions-International/antikythera)
- [Portfolio](https://kavindudamsith.tech/#work)

---

Questions about this repository? [Email me](mailto:kavindudamsith65@gmail.com) or connect on [LinkedIn](https://www.linkedin.com/in/kavindu-damsith-86696722a/).
