import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class HtmlInputArgs {
    private String name;
    private String type;
    private String placeholder;
}
